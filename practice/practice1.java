import java.util.*;


class Practice1 {
	
	Calendar current;
	
	public static void main(String[] args) {
	current = new GregorianCalendar();
	System.out.println(current.toString());
	
	}
	
	@Override
	public String toString(){
		return "Day: " + current.DAY + "Month: " + current.MONTH + "Year: "+ current.YEAR;
		
		
	}
}