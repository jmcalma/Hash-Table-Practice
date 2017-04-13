//Jerahmeel Calma
//CS240 Yu Sun
//Assignment 3
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Project3{
	
	HashTable<String, GenericArrayList<Double>> stats = new HashTable<String, GenericArrayList<Double>>();
	
	public static void main(String[] args) throws IOException{
		Project3 ht = new Project3();
		if(args.length < 1){
			System.out.println("Error: Directory name is missing");
			System.out.println("Usage: java scoreProcess directory_name");
			return;
		}
		File directory = new File(args[0]);
		File[] files = directory.listFiles();
		
		ht.addStats(files);
		//keeps asking for a name to input
		while(true){
			ht.getStat();
		}
	}
	
	//Reads in a directory of files, of statistics, from the command line.
	//Adds the data to a hashtable.
	public void addStats(File[] files) throws IOException{
		for(int i = 0; i < files.length; i++){
			Scanner input = new Scanner(files[i]);

			while(input.hasNext()){
				String name = "";
				while(!input.hasNextDouble()){
					String item = input.next();
					//throws an exception when the last word of a statistic is not a number
					if(item.contains("/n") && (item.charAt(0) < 48 && item.charAt(0) > 57)){
						throw new RuntimeException("There is an invalid statistic.");
					}
					name += item + " ";
				}
				Double score = new Double(input.next());

				GenericArrayList<Double> s = stats.get(name.trim());
				if(s == null){
					s = new GenericArrayList<Double>();
				}
				s.add(score);
				stats.put(name.trim(), s);
			}
		}
	}
	
	//Looks through the hashtable for the inputed name and calculates the average
	//score the inputed name has. Then displays the average score and number of scores.
	public void getStat(){
		int counter = 0;
		double average = 0.0;

		System.out.println("Enter name: ");
		Scanner input = new Scanner(System.in);
		String search = input.nextLine();
		
		GenericArrayList<Double> s = stats.get(search.trim());

		if(stats.get(search) == null){
			System.out.println(search + " not found");
		}
		else{
			for(int i = 0; i < s.size(); i++){
				counter++;
				average += s.get(i);
			}
			average /= counter;
			System.out.println(search + ": Avg: " + average + "  #Scores: " + counter);
		}
	}

}