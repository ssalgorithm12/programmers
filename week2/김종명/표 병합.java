package Programmers;

import java.util.ArrayList;

// 50.0 / 100.0

public class 표병합 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		solution(new String[] {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"});
	}
	
	static Cell[][] cell;
	static ArrayList<String> answer;
	
	static class Cell{
		
		int r;
		int c;
		String value;
	
		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
			this.value = "EMPTY";
		}
	}
	
    static public String[] solution(String[] commands) {
        
    	answer = new ArrayList<>();
    	
    	makeCell();
    	
    	int size = commands.length;
    	
    	for(int i=0; i<size; i++) {
    		
    		findCommand(commands[i]);
    		
    		for(int j=0; j<5; j++) {
    			for(int k=0; k<5; k++) {
    				
    				System.out.print(cell[j][k].value + " ");
    				
    			}
    			System.out.println("");
    		}
    		
    		System.out.println("-----------------------");
    	}
    	
    	String[] result = new String[answer.size()];
    	
    	for(int i=0; i<answer.size(); i++) {
    		
    		result[i] = answer.get(i);
    	}
    	
        return result;
    }
    
    // 표 만들기
    static void makeCell() {
    	
    	cell = new Cell[51][51];
    	
    	for(int r=0; r<51; r++) {
    		for(int c=0; c<51; c++) {
    			
    			cell[r][c] = new Cell(r, c);
    		}
    	}
    }
    
    // Update 1
    static void updateValue(String r, String c, String value) {
    	
    	int row = Integer.parseInt(r);
    	int col = Integer.parseInt(c);
    	
    	for(int i=0; i<51; i++) {
    		for(int j=0; j<51; j++) {
    			
    			if(cell[i][j].r == cell[row][col].r && cell[i][j].c == cell[row][col].c) {
    				
    				cell[i][j].value = value;
    				
    			}
    		}
    	}
    }
    
    // Update 2
    static void updateValue(String value1, String value2) {
    	
    	for(int i=0; i<51; i++) {
    		for(int j=0; j<51; j++) {
    			
    			if(cell[i][j].value.equals(value1)) {
    				
    				cell[i][j].value = value2;
    				
    			}
    		}
    	}
    }
    
    // Merge
    static void mergeCell(String r1, String c1, String r2, String c2) {
    	
    	int row_1 = Integer.parseInt(r1);
    	int col_1 = Integer.parseInt(c1);
    	int row_2 = Integer.parseInt(r2);
    	int col_2 = Integer.parseInt(c2);
    	
    	if(row_1 == row_2 && col_1 == col_2) {
    		
    		return;
    	}
    	
    	if(cell[row_1][col_1].value.equals("EMPTY") && cell[row_2][col_2].value.equals("EMPTY")) {
    		
    		return;
    	}
    	
    	if(cell[row_1][col_1].value.equals("EMPTY")) {
    		
    		for(int i=0; i<51; i++) {
        		for(int j=0; j<51; j++) {
        			
        			if(cell[i][j].r == cell[row_1][col_1].r && cell[i][j].c == cell[row_1][col_1].c){
        				
        				cell[i][j].r = cell[row_2][col_2].r;
        				cell[i][j].c = cell[row_2][col_2].c;
        				cell[i][j].value = cell[row_2][col_2].value;
        				
        			}
        		}
        	}
    	}else {
    		
    		for(int i=0; i<51; i++) {
        		for(int j=0; j<51; j++) {
        			
        			if(cell[i][j].r == cell[row_2][col_2].r && cell[i][j].c == cell[row_2][col_2].c){
        				
        				cell[i][j].r = cell[row_1][col_1].r;
        				cell[i][j].c = cell[row_1][col_1].c;
        				cell[i][j].value = cell[row_1][col_1].value;
        				
        			}
        		}
        	}
    	}
    }
    
    // Unmerge
    static void unmergeCell(String r, String c) {
    	
    	int row = Integer.parseInt(r);
    	int col = Integer.parseInt(c);
    	
    	int mr = cell[row][col].r;
    	int mc = cell[row][col].c;
    	
    	for(int i=0; i<51; i++) {
    		for(int j=0; j<51; j++) {
    			
    			if(i == row && j == col) {
    				
    				cell[i][j].r = i;
    				cell[i][j].c = j;
    				continue;
    			}
    			
    			if(cell[i][j].r == mr && cell[i][j].c == mc) {
    				
    				cell[i][j].r = i;
    				cell[i][j].c = j;
    				cell[i][j].value = "EMPTY";
    			}
    		}
    	}
    }
    
    // Print
    static void printCell(String r, String c) {
    	
    	int row = Integer.parseInt(r);
    	int col = Integer.parseInt(c);
    	
    	answer.add(cell[row][col].value);
    }
    
    // Find Command
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
