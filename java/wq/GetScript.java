package wq;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class GetScript {
	
	private static final String rootPath = "/Users/songmingu/Documents/w5test";
	private static final String savePath = rootPath + "/../_xmlscript/";
	

	public static void main(String[] args) {
		File targetDir = new File(savePath);
		recursiveDelete(targetDir);
		
        if(!targetDir.exists()) {    //디렉토리 없으면 생성.
        	targetDir.mkdirs();
        }
        
		File file = new File(rootPath);
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			makeJs(fileList[i], savePath);	
		}
	}

    public static void recursiveDelete(File file) {
        //to end the recursive loop
        if (!file.exists())
            return;
         
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        //System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }
	
	private static void makeJs(File file, String savePath){

		String name = file.getName();

		if(file.isDirectory()){
			String path = savePath + "/" + name;

			File targetDir = new File(path);  
	        if(!targetDir.exists()) {    //디렉토리 없으면 생성.
	        	targetDir.mkdirs();
	        }
			
			File[] fileList = file.listFiles();
			int xmlcnt = 0;
			for (int i = 0; i < fileList.length; i++) {
				makeJs(fileList[i], path);
				
				String name2 = fileList[i].getName();
				if(name2.endsWith("xml")){
					xmlcnt++;
				}
			}
			
			File[] list = targetDir.listFiles();
			if(list.length == 0){
				targetDir.delete();
			}
		}else if(name.endsWith("xml")){
			String absolutePath = file.getAbsolutePath();
			try{
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();

				//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				NodeList nList = doc.getElementsByTagName("script");
				//System.out.println("-----------------------");

				String script = "";
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						Node nValue = eElement.getFirstChild();
						script += nValue.getNodeValue();
					}
				}
				if(script.trim().length() > 0){
					String outputFilename = savePath + "/" + name + ".js";
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename),"UTF-8"));
				    out.write(script);
				    out.close();
				}
				
			    
			}catch (Exception e){
				System.out.println("error" + absolutePath);
				e.printStackTrace();
			}
		}
	}
}
