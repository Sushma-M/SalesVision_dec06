/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.hrdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.hrdb.Department;
import com.hrdb.Employee;
import com.hrdb.service.DepartmentService;

/**
 * Controller object for domain model class Department.
 * @see Department
 */
@RestController("hrdb.DepartmentController")
@Api(value = "DepartmentController", description = "Exposes APIs to work with Department resource.")
@RequestMapping("/hrdb/Department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    @Qualifier("hrdb.DepartmentService")
    private DepartmentService departmentService;

    @ApiOperation(value = "Creates a new Department instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Department createDepartment(@RequestBody Department department) {
        LOGGER.debug("Create Department with information: {}", department);
        department = departmentService.create(department);
        LOGGER.debug("Created Department with information: {}", department);
        return department;
    }

    @ApiOperation(value = "Returns the Department instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Department getDepartment(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Department with id: {}", id);
        Department foundDepartment = departmentService.getById(id);
        LOGGER.debug("Department details with id: {}", foundDepartment);
        return foundDepartment;
    }

    @ApiOperation(value = "Updates the Department instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Department editDepartment(@PathVariable("id") Integer id, @RequestBody Department department) throws EntityNotFoundException {
        LOGGER.debug("Editing Department with id: {}", department.getDeptid());
        department.setDeptid(id);
        department = departmentService.update(department);
        LOGGER.debug("Department details with id: {}", department);
        return department;
    }

    @ApiOperation(value = "Deletes the Department instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteDepartment(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Department with id: {}", id);
        Department deletedDepartment = departmentService.delete(id);
        return deletedDepartment != null;
    }

    /**
     * @deprecated Use {@link #findDepartments(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Department instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Department> findDepartments(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Departments list");
        return departmentService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Department instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Department> findDepartments(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Departments list");
        return departmentService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportDepartments(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return departmentService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Department instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countDepartments(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Departments");
        return departmentService.count(query);
    }

    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the employees instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Employee> findAssociatedEmployees(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated employees");
        return departmentService.findAssociatedEmployees(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service DepartmentService instance
	 */
    protected void setDepartmentService(DepartmentService service) {
        this.departmentService = service;
    }
}
