package com.ers.dao.interfaces;

import java.util.TreeSet;

import com.ers.model.Expense;

public interface ExpenseDAOInterface {
	
	// CREATE
		public void insertExpense(Expense exp, boolean isBlob);

		// READ
		public TreeSet<Expense> selectExpensesByEmployeeId(int reimb_author);
		
		public TreeSet<Expense> selectExpensesByReimbursementStatus(int reimb_status_id);
		
		public TreeSet<Expense> selectAllExpenses();

		// UPDATE
		void updateExpense(Expense exp);
		void approveExpense(int reimb_id);
		void denyExpense(int reimb_id);

		// DELETE
		void deleteExpense(Expense exp);
		
		//Generate Reim_id
		public int  generateReimbId();
	

}
