package test;

public class Bean
	
{
	private String name;
	
	public void setName(String name){
		this.name=name;	
	}

	public String getName(){
		return this.name;		
	}

	public void set(){
		new Bean().setName("jackie");
		
	}
}
