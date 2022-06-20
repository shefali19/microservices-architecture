package com.learning.user.services.controller;

import com.learning.user.services.dto.ResponseTemplate;
import com.learning.user.services.dto.UserDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@Api(tags={"users"})
public interface UserAPI {

    @ApiOperation(
            value = "Create User",
            notes = "User has to be created to start working on services", response = ResponseEntity.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User has been successfully created "),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserDetails user) throws Exception;

    @ApiOperation(
            value = "Search particular User details",
            notes = "Enter valid search parameters to fetch relevant info", response = ResponseEntity.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search is success "),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @PostMapping(value = "/search")
    public ResponseEntity<?> searchUser(@RequestBody UserDetails user) throws Exception;

    @ApiOperation(
            value = "Fetch all relevant User details",
            notes = "fetch all usr details", response = ResponseEntity.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fetch call is success "),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @GetMapping(value = "/fetchall")
    public ResponseEntity<?> fetchAllUsers() throws Exception;

    @ApiOperation(
            value = "Fetch all values of particular user including document details and department values",
            notes = "User has to be created to start working on services", response = ResponseTemplate.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "relevant user details has been fetch successfully "),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @GetMapping(value = "/{id}")
    public ResponseTemplate getUserDetailsById(@ApiParam(name="userId") @PathVariable("id") long userId) throws Exception;



}
