 package com.shukhrat.expensetrackerapi.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shukhrat.expensetrackerapi.entity.Expense;
import com.shukhrat.expensetrackerapi.service.ExpenseService;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page) {
		return expenseService.getAllExpenses(page).toList();
	}	
	
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
		return expenseService.getExpenseById(id);
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam Long id) {
		expenseService.deleteExpenseById(id);
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public Expense saveExpense(@Valid @RequestBody Expense expense) {
		return expenseService.saveExpense(expense);
	}
	
	@PutMapping("/expenses/{id}")
	public Expense updateExpenseById(@PathVariable Long id, @RequestBody Expense expense) {
		return expenseService.updateExpenseById(id, expense);
	}
	
	@GetMapping("/expenses/category")
	public List<Expense> readByCategory(@RequestParam String category, Pageable page){
		return expenseService.readByCategory(category, page);
	}
	
	@GetMapping("/expense/name")
	public List<Expense> readByName(@RequestParam String name, Pageable page){
		return expenseService.readByName(name, page);
	}
	
	@GetMapping("/expense/date")
	public List<Expense> readByDate(@RequestParam Date startDate, 
			                        @RequestParam Date endDate,
			                        Pageable page){
		return expenseService.readByDate(startDate, endDate, page);
	}
}
