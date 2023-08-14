package com.practice.mangodbcrud.service;

import com.practice.mangodbcrud.model.Expense;
import com.practice.mangodbcrud.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);

    }
    public void updateExpense(Expense expense){
            Expense savedExpense = expenseRepository.findById(expense.getId()).orElseThrow(() -> new RuntimeException(String.format("Cannot Find Expense by ID %s", expense.getId())));
            savedExpense.setExpenseName(expense.getExpenseName());
            savedExpense.setExpenseCategory(expense.getExpenseCategory());
            savedExpense.setExpenseAmount(expense.getExpenseAmount());

            expenseRepository.save(savedExpense);
    }
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }
    public Expense getExpenseByName(String name){
        return expenseRepository.findByName(name).orElseThrow(()-> new RuntimeException(
                String.format("Cannot find expense by Name %s", name)
        ));
    }
    public void deleteExpense(String Id){
        expenseRepository.deleteById(Id);
    }

}
