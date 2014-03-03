import java.util.Scanner;


public class AdjacencyMatrix {

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
			
			int matrix[][] = new int[nodeN+1][nodeN+1];
			
			if ( nodeN >= 1 && nodeN <= 5000 && edgeM >= 1 && edgeM <= 10000 ) {
								
				for ( int j=0; j<edgeM; j++) {
					matrix[sc.nextInt() + 1][sc.nextInt() + 1] = sc.nextInt();
					sc.nextLine();
				}
				
				System.out.printf("%s%n", i+1);
				
				for ( int j=0; j < (nodeN+1); j++) {
					
					for ( int n=0; n < (nodeN+1); n++) {
						if ( j == 0 && n == 0 ) {
							System.out.printf("     ");
						}
						else if ( j == 0 && n > 0 || j > 0 && n == 0 ) {
							System.out.printf("%5d", ((j > 0 ? j : n) - 1));
						}
						else {
							System.out.printf("%5d", matrix[j][n]);
						}
					}
					System.out.println();
				}
			}
		}
	}

}
