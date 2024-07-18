package com.example.spring_0715.day0718_restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private List<MemberDto> members = new ArrayList<>();
    private long nextId = 1;

    @GetMapping // GET /api/members
    public List<MemberDto> getMembers() {
        return members;
    }

    @PostMapping // POST /api/members
    public MemberDto createMember(@RequestBody MemberDto member) {
        member.setId(nextId++);
        members.add(member);
//        HttpStatus.CREATED;
        return member; // member 객체 반환. json
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable("id") Long id){
        MemberDto memberDto =  members.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok(memberDto);
        // return ResponseEntity.status(404).body(member); // 정상적으로 반환되었지만, 404 뜸.
    }

    @PutMapping("/{id}")
    public MemberDto updateMember(@PathVariable("id") Long id, @RequestBody Member updateMember) {
        MemberDto member = members.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾지 못했습니다."));

        member.setName(updateMember.getName());
        member.setEmail(updateMember.getEmail());
        return member;
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") Long id) {
        members.removeIf(member -> member.getId() == id);
    }




}
