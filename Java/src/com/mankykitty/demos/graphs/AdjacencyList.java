import java.util.ArrayList;
import java.util.Scanner;


public class AdjacencyList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int caseNo = sc.nextInt();
		
		for (int i=0;i<caseNo;i++) {
			int nodeN = sc.nextInt();
			int edgeM = sc.nextInt();
			
			sc.nextLine();
									
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			
			for ( int n=0;n<nodeN;n++ ) {
				ArrayList<Integer> node = new ArrayList<Integer>();
				list.add(node);
			}
			
			if ( nodeN >= 1 && nodeN <= 5000 && edgeM >= 1 && edgeM <= 10000 ) {
			
				for ( int j=0; j < edgeM; j++ ) {
					ArrayList<Integer> node = list.get(sc.nextInt());
					node.add(sc.nextInt());
					list.add(j, node);
				}
				
				System.out.printf("%s%n", i + 1);
				
				for ( ArrayList<Integer> node : list ) {
					System.out.printf("%s: ", list.indexOf(node));
										
					for ( int vert : node ) {
						System.out.printf("%s, ", vert);
					}
					System.out.println();
				}
			}
		}
	}

}
