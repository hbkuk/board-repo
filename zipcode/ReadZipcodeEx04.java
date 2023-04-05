import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadZipcodeEx04 {

	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		
		String juso = scan.nextLine();
		
		juso = juso.replace(" ", "");
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader( new FileReader("./zipcode_seoul_utf8_type2.csv"));
			
			System.out.println( juso + "를 입력받았습니다." + "\n");
			
			String str = null;
			
			int Numbering = 0;
			
			while( (str = br.readLine()) != null ) {
				String[] addresses = str.split( "," );

				if( addresses[3].startsWith(juso)) {
					Numbering++;
					System.out.println( "["+Numbering+"]"+ "\t" + str + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println( e.getMessage());
		} catch (IOException e) {
			System.out.println( e.getMessage());
		} finally {
			if( br != null ) try { br.close(); } catch(IOException e) {}
		}
	}
}
