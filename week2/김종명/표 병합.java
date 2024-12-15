package programmers;

import java.util.ArrayList;

public class 표병합 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static Cell[][] cell;
	static ArrayList<String> answer;
	
	static class Cell{
		
		String value;
		ArrayList<Cell> friends;
	
		
		public Cell() {
			friends = new ArrayList<>();
			this.value = "EMPTY";
		}
	}
	
    public String[] solution(String[] commands) {
        
    	answer = new ArrayList<>();
    	
    	makeCell();
    	
    	int size = commands.length;
    	
    	for(int i=0; i<size; i++) {
    		
    		findCommand(commands[i]);
    	}
    	
    	String[] result = new String[answer.size()];
    	
    	for(int i=0; i<answer.size(); i++) {
    		
    		result[i] = answer.get(i);
    	}
    	
        return result;
    }
    
    static void makeCell() {
    	
    	cell = new Cell[51][51];
    	
    	for(int r=0; r<51; r++) {
    		for(int c=0; c<51; c++) {
    			
    			cell[r][c] = new Cell();
    		}
    	}
    }
    
    static void updateValue(String r, String c, String value) {
    	
    	int row = Integer.parseInt(r);
    	int col = Integer.parseInt(c);
    	

    }
    
    static void updateValue(String value1, String value2) {
    	
  
    }
    
    static void mergeCell(String r1, String c1, String r2, String c2) {
    	
    	int row_1 = Integer.parseInt(r1);
    	int col_1 = Integer.parseInt(c1);
    	int row_2 = Integer.parseInt(r2);
    	int col_2 = Integer.parseInt(c2);
    	
    	if(row_1 == row_2 && col_1 == col_2) {
    		
    		return;
    	}
    		
    }
    
    static void unmergeCell(String r, String c) {
    	
    	int row = Integer.parseInt(r);
    	int col = Integer.parseInt(c);
    	
	
    }
    
    static void printCell(String r, String c) {
    	
    	int row = Integer.parseInt(r);
    	int col = Integer.parseInt(c);
    	
    	answer.add(cell[row][col].value);
    }
    
    static void findCommand(String command) {
    	
    	String[] com = command.split(" ");
    	
    	String order = com[0];
    	
    	if(order.equals("UPDATE")) {
    		
    		if(com.length == 4) {
    			
    			updateValue(com[1], com[2], com[3]);
    			
    		}else if(com.length == 3) {
    			
    			updateValue(com[1], com[2]);
    		}
    		
    	}else if(order.equals("MERGE")) {
    		
    		mergeCell(com[1], com[2], com[3], com[4]);
    		
    	}else if(order.equals("UNMERGE")) {
    		
    		unmergeCell(com[1], com[2]);
    		
    	}else if(order.equals("PRINT")) {
    		
    		printCell(com[1], com[2]);
    	}
    	
    }
    
}
