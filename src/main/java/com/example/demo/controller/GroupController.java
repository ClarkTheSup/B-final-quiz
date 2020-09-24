package com.example.demo.controller;

import com.example.demo.domain.Group;
import com.example.demo.dto.GroupDto;
import com.example.demo.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GroupController {
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<GroupDto> getGroups() {
        return groupService.getGroups();
    }

    @PostMapping("/groups/auto-grouping")
    public void createGroups() {
        groupService.createGroups();
    }

    @PatchMapping("/groups/{group_id}")
    public void renameGroup(@PathVariable Long group_id, @RequestBody String group_name) {
        groupService.renameGroup(group_id, group_name);
    }
}
