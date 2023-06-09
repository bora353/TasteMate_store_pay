package com.tastemate.service;

import com.tastemate.domain.MemberVO;
import com.tastemate.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MemberService {

    private MemberMapper mapper;

    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    public MemberService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    public List<MemberVO> user_get() {

        List<MemberVO> memberList = mapper.member_getList();

        return memberList;
    }

    public void user_join(MemberVO vo, MultipartFile multipartFile) {

        log.info("service도착");
        String savedName = null;

        if(multipartFile == null){
            log.info("empty 확인");
            savedName = "tastemate.jpg";

        } else {
            String oriFilename = multipartFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String extension = oriFilename.substring(oriFilename.lastIndexOf("."));

            oriFilename = oriFilename.substring(oriFilename.lastIndexOf("\\")+1);

            savedName = uuid + "_" + oriFilename;

            String savedPath = fileDir + "/" +savedName;

            log.info(savedPath);

            File saveFile = new File(savedPath);

            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            } //else 끝

        MemberVO vo1 = new MemberVO();

        String userAddress = String.join("/", vo.getUserAddress());
        String userBirth = String.join("/", vo.getUserBirth());


        System.out.println("Id = " + vo.getUserId());
        System.out.println("pwd = " + vo.getUserPwd());
        System.out.println(vo.getUserName());
        System.out.println(vo.getUserGender());
        System.out.println(vo.getUserPhone());
        System.out.println("userAddress = " + userAddress);
        System.out.println(vo.getUserClass());
        System.out.println(vo.getUserEmail());
        System.out.println(vo.getUserLikeFood());
        System.out.println(vo.getUserBirth());
        System.out.println(vo.getUserMbti());
        System.out.println("savedName = " + savedName);


        vo1.setUserId(vo.getUserId());
        vo1.setUserPwd(vo.getUserPwd());
        vo1.setUserName(vo.getUserName());
        vo1.setUserGender(vo.getUserGender());
        vo1.setUserPhone(vo.getUserPhone());
        vo1.setUserAddress(userAddress);
        vo1.setUserLikeFood(vo.getUserLikeFood());
        vo1.setUserClass(vo.getUserClass());
        vo1.setUserEmail(vo.getUserEmail());
        vo1.setUserMbti(vo.getUserMbti());
        vo1.setUserBirth(userBirth);
        vo1.setUserProfile(savedName);

        int result = mapper.user_join(vo1);
    }

    public Integer checkId(String userId) {
        return mapper.checkId(userId);
    }

    public MemberVO loginId(String userId) {
        MemberVO vo = mapper.loginId(userId);
        return vo;
    }

    public void userModify(MemberVO vo, MultipartFile multipartFile, HttpSession session, HttpServletRequest request) {

        log.info("수정service도착");

        MemberVO sessionVO = (MemberVO) session.getAttribute("sessionVO");

        String getVoUserAddress = vo.getUserAddress();

        System.out.println("getVoUserAddress = " + getVoUserAddress);

        String userAddress = String.join("/", request.getParameter("userAddress"));

        System.out.println("------------SERVICE---------------");
        String userAddress1 = request.getParameter("userAddress1");
        String userAddress2 = request.getParameter("userAddress2");
        String userAddress3 = request.getParameter("userAddress3");
        String userAddressAll = userAddress1 + "," + userAddress2 + "," + userAddress3;
        System.out.println("수정 전 vo = " + vo);
        System.out.println("sessionVO = " + sessionVO);

        vo.setUserIdx(sessionVO.getUserIdx());

        if (request.getParameter("userName").isEmpty()) {
            vo.setUserName(vo.getUserName());
        } else {
            vo.setUserName(request.getParameter("userName"));
        }

        if (request.getParameter("userEmail").isEmpty()) {
            vo.setUserEmail(vo.getUserEmail());
        } else {
            vo.setUserEmail(request.getParameter("userEmail"));
        }

        if (request.getParameter("userPhone").isEmpty()) {
            vo.setUserPhone(vo.getUserPhone());
        } else {
            vo.setUserPhone(request.getParameter("userPhone"));
        }

        if (request.getParameter("userLikeFood").isEmpty()) {
            vo.setUserLikeFood(vo.getUserLikeFood());
        } else {
            vo.setUserLikeFood(request.getParameter("userLikeFood"));
        }

        if (request.getParameter("userMbti").isEmpty()) {
            vo.setUserMbti(vo.getUserMbti());
        } else {
            vo.setUserMbti(request.getParameter("userMbti"));
        }

        if (request.getParameter("userAddress1").isEmpty()) {
            vo.setUserAddress(vo.getUserAddress());
            System.out.println("if문 안에 getVoUserAddress = " + getVoUserAddress);
        } else {
            vo.setUserAddress(userAddressAll);
        }

        System.out.println("수정 후 vo = " + vo);

        int result = mapper.userModify(vo);
    }

    public void userStatus(MemberVO vo, HttpSession session) {

        log.info("탈퇴service도착");

        MemberVO sessionVO = (MemberVO) session.getAttribute("sessionVO");

        System.out.println("sessionVO = " + sessionVO);

        vo.setUserIdx(sessionVO.getUserIdx());



        int result = mapper.userStatus(vo);

    }

    public String findId(String userEmail) {
        MemberVO vo = mapper.findId(userEmail);
        String userId = vo.getUserId();
        return userId;
    }

}

