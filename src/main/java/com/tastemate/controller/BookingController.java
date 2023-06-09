package com.tastemate.controller;

import com.tastemate.domain.BookingVO;
import com.tastemate.domain.BookmarkVO;
import com.tastemate.domain.MemberVO;
import com.tastemate.domain.StoreVO;
import com.tastemate.service.BookingService;
import com.tastemate.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping("/booking/*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private StoreService storeService;

    @RequestMapping("/bookingList")
    public String getList(Model model) {
        List<BookingVO> bookingList = bookingService.bookingList();
        System.out.println("bookingList = " + bookingList);
        model.addAttribute("bookingList", bookingList);

        return "/booking/bookingList";
    }

    @GetMapping ("/bookingInsert")
    public void booking(Model model, HttpServletRequest request, HttpSession session, int storeIdx) {
        MemberVO memberVO = (MemberVO) session.getAttribute("vo");

        String userId = memberVO.getUserId();
        String userName = memberVO.getUserName();;

        StoreVO storeVO = storeService.store_get(storeIdx);
        System.out.println("storeVO = " + storeVO);
        model.addAttribute("storeVO", storeVO);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
    }


    @PostMapping("/bookingInsert")
    public String bookingInsert(Model model, BookingVO bookingVO,
                                @RequestParam String category1, @RequestParam String tableNo,
                                @RequestParam String selectDate, @RequestParam String time, @RequestParam String onsitePayment) {
        System.out.println("date = " + selectDate);
        System.out.println("time = " + time);
        // 문자열
        String dateStr = selectDate + " " +time;
        System.out.println("dateStr = " + dateStr);


        bookingVO.setBookingTime(dateStr);
        System.out.println("bookingVO.getBookingTime() = " + bookingVO.getBookingTime());

        System.out.println("category1 = " + category1);
        String[] info = category1.split("/");
        bookingVO.setMenuIdx(Integer.parseInt(info[0]));
        bookingVO.setFoodName(info[1]);
        bookingVO.setPrice(Integer.parseInt(info[2]));
        bookingVO.setTableNo(Integer.parseInt(tableNo));
        bookingVO.setOnsitePayment(Integer.parseInt(onsitePayment));




        System.out.println("bookingVO = " + bookingVO);


        int result = bookingService.bookingToPay(bookingVO);


        System.out.println("result = " + result);
        return "/booking/bookingToPay";
    }


}
