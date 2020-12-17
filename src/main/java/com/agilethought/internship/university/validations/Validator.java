package com.agilethought.internship.university.validations;

import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.UpdateCourseRequest;
import com.agilethought.internship.university.exception.BadRequestException;
import com.agilethought.internship.university.service.common.CategoryConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class Validator {

    public static void requestValidation(CreateCourseRequest request) {
        if(StringUtils.isBlank(request.getCategory()))
            throw new BadRequestException("Category field is required", "/course/");
        log.debug("Category field validation successful");
        if (!CategoryConstants.getCategoryNames().contains(request.getCategory().toUpperCase()))
            throw new BadRequestException("Incorrect category value", "/course/");
        log.debug("Category field create content validation successful");
        if(StringUtils.isBlank(request.getTitle()))
            throw new BadRequestException("Title field is required", "/course/");
        log.debug("Title field validation successful");
        if(StringUtils.isBlank(request.getDescription()))
            throw new BadRequestException("Description field is required", "/course/");
        log.debug("Description field validation successful");
        if(Objects.isNull(request.getStatus()))
            throw new BadRequestException("Status field is required", "/course/");
        log.debug("Status field validation successful");
        if((request.getStatus()!=0) && (request.getStatus()!=1))
            throw new BadRequestException("Bad status value", "/course/");
        log.debug("Category field create content validation successful");
    }

    public static void requestValidationToUpdate(UpdateCourseRequest request, String id) {
        if (StringUtils.isBlank(id))
            throw new BadRequestException("Course ID is required to update","/course/");
        if (!CategoryConstants.getCategoryNames().contains(request.getCategory().toUpperCase()))
            throw new BadRequestException("Incorrect category value", "/course/");
        log.debug("Category field update content validation successful");
        if(StringUtils.isBlank(request.getTitle()))
            throw new BadRequestException("Title field is required to update", "/course/");
        if(StringUtils.isBlank(request.getDescription()))
            throw new BadRequestException("Description field is required to update", "/course/");
        if((request.getStatus()!=0) && (request.getStatus()!=1))
            throw new BadRequestException("Bad status value", "/course/");
        log.debug("Category field update content validation successful");
    }

    public static void validationToDelete(String id) {
        if (StringUtils.isBlank(id))
            throw new BadRequestException("Course ID is required to delete","/course/");
    }
}
