package application;

public class RLESys {


			String plainText;
			String encodedText;
			StringBuilder decodedText = new StringBuilder();

			RLESys (String plainText, String encodedText) {
				this.plainText = plainText;
				this.encodedText = encodedText;
			  }
				
				// PRZECIĄŻONY KONSTRUKTOR
			RLESys (String encodedText, StringBuilder decodedText) {
				this.encodedText = encodedText;
				this.decodedText = decodedText;
				  }

				//KODOWANIE   
		public String encode(String plainText) {
			 
		  int count = 1;
		  for (int i = 0; i < plainText.length(); i++) {
		   count = 1;
		   while (i < plainText.length() - 1 && plainText.charAt(i) == plainText.charAt(i + 1)) {
		    count++;
		    i++;
		   }
		   encodedText = encodedText + plainText.charAt(i) + count + ',';
		  }
		  
		  System.out.println("Tekst do zakodowania: " + plainText);
		  System.out.println("Tekst zakodowany: " + encodedText.substring(0, encodedText.length()-1));
		return plainText;

		 }
		
				//DEKODOWANIE
		public String decode(String encodedText) {

		        String[] arrOfStr = encodedText.split(",");

		        for (int i = 0; i < arrOfStr.length; i++) {
		            String a = arrOfStr[i].substring(0,1);
		            String b = arrOfStr[i].substring(1);


		            if (encodedText.matches("([a-zA-Z0-9]{1}[1-9]+,{0,1})+") && (encodedText.contains(","))) {
		            for (int j = 1; j <= Integer.parseInt(b); j++) {
		            	decodedText.append(a);
		            }
		            
			        System.out.println("Tekst odkodowany: " + decodedText);
			        System.out.println("Tekst do odkodowania: " + encodedText);
		            
		            }
		            else {
				        System.out.println("Nieprawidłowy format źródła. \n"
				        		+ "Spróbuj wpisać tekst jeszcze raz oddzielając znaki symbolem przecinka "
				        		+ "np. A4,B3");
		            }
		        }
		        return encodedText;
			 }
}
