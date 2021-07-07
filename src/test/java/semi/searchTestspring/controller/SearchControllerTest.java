//package semi.searchTestspring.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.ResultActions;
//import semi.searchTestspring.domain.Library;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//class SearchControllerTest {
//    @Autowired
//    private Library library;
//
//    @Test
//    public void requestParameter의_LocalDate는_변환된다() throws Exception {
//        //given
//        String url = "/requestParameter?requestDateTime=2018-12-15T10:00:00";
//
//        //when
//        ResultActions resultActions = this.library
//
//        //then
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("requestParameter 성공")));
//
//    }