package com.ssafy.ssap.controller;

import com.ssafy.ssap.common.MessageFormat;
import com.ssafy.ssap.dto.RoomCreateDto;
import com.ssafy.ssap.service.RoomService;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    private final RoomService roomService;


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody RoomCreateDto roomCreateDto) {
        Map<String, Object> resultMap;
        HttpStatus status;
        try {
            //openvidu session, connection 생성
            resultMap = roomService.makeSession(roomCreateDto); //openviduDto에 session, connection, token 담음
            status = HttpStatus.OK;
        } catch (Exception e) {
            resultMap = new HashMap<>();
            resultMap.put("message","스터디룸 생성 중 에러 발생");
            logger.error("스터디룸 생성 실패: ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @DeleteMapping("/{roomno}")
    public ResponseEntity<?> close(@PathVariable("roomno") Integer roomNo) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            roomService.close(roomNo);
            logger.debug("{} 스터디룸 폐쇄 성공", roomNo);
            resultMap.put("message", MessageFormat.SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("스터디룸 폐쇄 실패: ", e);
            resultMap.put("message", MessageFormat.SERVER_FAIL + ": " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PutMapping("/host")
    public ResponseEntity<?> changeHost(@RequestBody Map<String, Integer> changeInfo){
        /* roomNo, currentUserNo, nextUserNo */
        HttpStatus status;
        logger.debug("host 변경 controller 호출");
        try{
            roomService.changeHost(changeInfo.get("roomNo"), changeInfo.get("currentUserNo"),changeInfo.get("nextUserNo"));
            status = HttpStatus.OK;
        } catch(Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/role")
    public ResponseEntity<?> assignStaff(@RequestBody Integer roomNo, Integer userNo){
        /*
          assignInfo.get("roomNo"), assignInfo.get("participantsNo")
          participants테이블의 room_id = roomNo and user_id = participantsNo 조건에 해당하는 유저의 role을 `스태프`으로 바꾼다.
          + 권한부여
         */
        HttpStatus status;
        logger.trace("staff 임명 controller 호출 with "+userNo);
        try{
            roomService.assignStaff(roomNo, userNo);
            status = HttpStatus.OK;
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

    @Transactional
    @PostMapping("/kick")
    public ResponseEntity<?> kick(@RequestBody Map<String, Object> kickInfo){
        /*
          kickInfo.get("roomNo"), kickInfo.get("participantsNo"), kickInfo.get("reason")
         */
        logger.debug("rooms/kick controller 호출 with : "+kickInfo.toString());
        HttpStatus status;
        try{
            roomService.kickAndAlarm(
                    (Integer) kickInfo.get("roomNo"),
                    (Integer) kickInfo.get("staffNo"),
                    (Integer) kickInfo.get("targetNo"),
                    (String) kickInfo.get("reason")
            );
            status = HttpStatus.OK;
        } catch(Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

    @PostMapping("/code/{roomNo}")
    public ResponseEntity<?> getEnterCode(@PathVariable Integer roomNo){
        /*
          무작위 세자리 code리턴.
         */
        logger.trace(roomNo+"방의 코드 생성 요청");
        Map<String, String> resultMap;
        HttpStatus status;
        try{
            resultMap = roomService.createCode(roomNo);
            status = HttpStatus.OK;
        } catch(Exception e){
            resultMap = new HashMap<>();
            resultMap.put("message","Error while Creating Room Number");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap,status);
    }

    @GetMapping("/wholeList")
    public ResponseEntity<Map<String,Object>> search(){
        /*
          keyword로 where = keyword 검색 쿼리 날린 결과 돌려주기
          @return : roomNo(int) roomTitle(String) quota(int) participantsCnt(int) roomImagePath(String)
         * pageLimit(미정) 개수만큼 자르기
         */
        HttpStatus status;
        Map<String,Object> resultMap = new HashMap<>();
        try {
            roomService.getEntireRoomList(resultMap);
            status = HttpStatus.OK;
        } catch(Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @GetMapping("/list")
    public ResponseEntity<?> search(@RequestParam String keyword,
                                    @RequestParam Integer pageNo,
                                    @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        logger.debug("controller 진입 "+keyword+"/"+pageNo+"/"+pageSize);
        HttpStatus status;
        Map<String,Object> resultMap = new HashMap<>();
        try {
            resultMap = roomService.getRoomList(keyword, pageNo, pageSize);
            status = HttpStatus.OK;
        } catch(Exception e){
            resultMap.put("message","방목록 불러오기 실패");
            logger.error("방 검색 실패 "+e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Deprecated
    @GetMapping("/detail/{roomNo}")
    public ResponseEntity<?> detail(@PathVariable Integer roomNo){
        /*
          roomNo에 해당하는 room에 대한 정보(roomDto) return
          participant 정보가 필요하여 넘겨주는 것이 아니면 구현X
         */
        logger.trace(roomNo+"방의 정보 요청");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/{roomNo}")
    public ResponseEntity<?> join(@PathVariable Integer roomNo, @RequestBody Map<String,Object> map) {
        HttpStatus status;
        String token;

        try{
            token = roomService.joinRoom(
                    roomNo,
                    (String) map.get("password"),
                    (Integer) map.get("userNo")
            );
            status = HttpStatus.OK;
        } catch(Exception e){
            logger.error("something's wrong"+e);
            token = null;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(token, status);
    }

    @GetMapping("/code/{roomCode}")
    public ResponseEntity<?> getRoom(@PathVariable String roomCode){
        Map<String, Object> resultMap;
        HttpStatus status;
        try{
            resultMap = roomService.getRoom(roomCode);
            status = HttpStatus.OK;
        }catch(Exception e){
            resultMap = new HashMap<>();
            resultMap.put("message","roomCode로 방 번호 조회 실패");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap,status);
    }

    @PostMapping("exit")
    public ResponseEntity<?> exit(@RequestBody Map<String, Integer>map) throws OpenViduJavaClientException, OpenViduHttpException {
        HttpStatus status = roomService.exit(map.get("roomNo"), map.get("userNo"));
        return new ResponseEntity<>(status);
    }

    @GetMapping("/currentConnection/{roomNo}")
    public ResponseEntity<?> checkConnection(@PathVariable Integer roomNo){
        HttpStatus status;
        Map<String, Object> resultMap;
        try{
            resultMap = roomService.checkConnection(roomNo);
            status = HttpStatus.OK;
        } catch(Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            resultMap = new HashMap<>();
            resultMap.put("meessage","connection 확인 중 실패");
        }
        return new ResponseEntity<>(resultMap, status);
    }
}
