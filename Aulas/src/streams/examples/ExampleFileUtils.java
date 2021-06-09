package streams.examples;

public class ExampleFileUtils {
	private static String DIR_TEST = "src\\streams";
	public static void main( String[] args ) {
		String testDir = DIR_TEST + "\\examples";
		String testFile = DIR_TEST + "\\examples\\StreamsUtils.java";
		//System.out.println( StreamsUtils.numberFiles( "C:\\"));
		System.out.println( "Number of files("+ testFile + ") -> "+ FileUtils.numberFiles( testFile ));
		System.out.println( "Number of files("+ testDir + ")-> "+ FileUtils.numberFiles( testDir));
		System.out.println( "Number of files("+ DIR_TEST + ")-> "+ FileUtils.numberFiles( DIR_TEST));
		System.out.println( "Number of files (\".\") -> "+ FileUtils.numberFiles( "."));
		System.out.println("------------------ list "+ testFile );
		FileUtils.list(testFile);
		System.out.println("------------------ list "+ testDir);
		FileUtils.list(testDir);
		System.out.println("------------------ dir " +testFile);
		FileUtils.dirAll(testFile);
		System.out.println("------------------ dir " +testDir);
		FileUtils.dirAll(testDir);
		System.out.println("------------------ dir " +DIR_TEST);
		FileUtils.dirAll(DIR_TEST);
	}
}
