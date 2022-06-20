package com.example.teamprojeect.controller.mypage;


import com.example.teamprojeect.domain.vo.user.UserVO;
import com.example.teamprojeect.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/myPage/*")
public class MypageController {
    // 필드 생성
    private final UserService userService;

    // 마이페이지 이동
    @GetMapping("/myPage")
    public String goMypage() {
        return "/myPage/myPage";
    }

    // 유저 정보 수정 - Talend API Tester 통과
    @PatchMapping(value={"/{userNumber}"}, consumes="application/json")
    @ResponseBody
    public String modify(@PathVariable("userNumber")Long userNumber, @RequestBody UserVO userVO){
        log.info("modify....................." + userVO);
        log.info("modify....................." + userNumber);

        userVO.setUserNumber(userNumber);
        userService.modify(userVO);
        return "유저 정보 수정 성공";
    }

    // 비밀번호 수정 - Talend API Tester 통과
    @PostMapping(value="/{userNumber}", consumes = "text/plain")
    @ResponseBody
    public String modifyPw(@PathVariable("userNumber") Long userNumber, @RequestBody String password){
        log.info("modifyPw.................." + userNumber);
        log.info("modifyPw.................." + password);

        userService.modifyPw(userNumber, password);
        return userNumber + "유저 비밀번호 수정 성공" + ":" + password;
    }

    // 회원 탈퇴 - Talend API Tester 통과
    @DeleteMapping("/{userNumber}")
    @ResponseBody
    public String remove(@PathVariable("userNumber")Long userNumber){
        userService.remove(userNumber);
        return "유저 삭제 성공";
    }
}
