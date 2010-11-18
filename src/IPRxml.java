import org.w3c.dom.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.*;

/** IPRxml class for the IPR project SOEN 6441, Fall 2010.
 * <p>
 * This class constructs an XML representation of the program outputs then create an XML
 *  file.
 * <p>
 * This class also transforms the XML file into more readable format
 * [HTML] through XSLT style sheet.
 * <p>
 * <b>Input:</b> <ul>
 *  <li>File path where the XML file will be saved. </li>
 *  <li>Prime number n.</li>
 * <li>Integral Degree Root d, d>1. </li>
 * </ul>
 * <p>
 * <b>Output: </b> <ul>
 * <li> XML file representing the program outputs. </li>
 * <li> an HTML format of the XML file to enhance readability. </li>
 * </ul>
 * <p>
 * <b>Libraries :</b><ul>
 * <li> org.w3c.dom.* </li>
 * <li> java.io.* </li>
 * <li> javax.xml.* </li>
 * </ul>
 * @author Feras AlJumah, Naif AlQahtan (Team E)
 *
 */
public class IPRxml {
	/**
	* Registers a path where the XML file will be saved.
	*/
    private String xmlFilePath;
	/**
	* Prime number [n] to calculate dth root for.
	*/
    private double Prime;
    /**
     * Integral Degree Root [d]
     */
    private int Integral_Degree_Root;
    
	/**
	* Supporting fields to populate the XML Document
	*/
    /**
     * Register an XML document
     */
    private Document xmlDocument;
    /**
     * Represents the Root Node of the XML document ["IPR"]
     */
    private Element RootNode;
    /**
     * Represents the Inputs Node of the XML document ["Inputs"]
     */
    private Element InputsNode;
    /**
     * Represents the Outputs Node of the XML document ["Outputs"]
     */
    private Element OutputsNode;
    /**
     * Represents the Errors Messages Node of the XML document ["Errors"]
     */
    private Element ErrorsMessage;
  
    /**
     * Class Constructor.
     * Passes the three expected inputs values [pathOfxmlFile, Prime number n, and Integral Degree Root d]
     * @exception ParserConfigurationException  [for the sake of catching other Non
     * Parser Configuration errors; general class Exception is used].
     * [ParserConfigurationException doesn't catch IO exceptions; but  general class Exception will trap
     * any violations may occur including ParserConfigurationException and IO errors].
     */
    public IPRxml(String xmlFilePath,double Prime, int Integral_Degree_Root) 
    {
    	this.xmlFilePath = xmlFilePath;
    	this.Prime = Prime;
    	this.Integral_Degree_Root = Integral_Degree_Root;
    	
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        
		try 
		{
			db = dbf.newDocumentBuilder();
			xmlDocument = db.newDocument();
			
			SetUpXmlStruct();	
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		}       
    }
    
    /**
     * 1. Registers the structure of the XML document. The elements nodes RootNode,
     * InputsNode, OutputsNode, and ErrosMessage construct the document as follow:
     * <pre>
     * {@code
     * <@see #RootNode::IPR>
     * 		<InputsNode::Inputs>
     * 			.....
     * 		</InputsNode>
     * 		<OutputsNode::Outputs>
     * 			........
     * 		</OutputsNode>
     * 		<ErrorsMessage::Errors>
     * 			if any
     * 		</ErrorsMessage>
     * </RootNode>
     * }
     * </pre><p>
     * 2. Populate InputsNode with the user inputs.
     * @see #SetUpInputNodes()
     * @see #RootNode
     * @see #InputsNode
     * @see #OutputsNode
     * @see #ErrorsMessage
     */
    private void SetUpXmlStruct()
    {
    	RootNode = xmlDocument.createElement("IPR");
    	RootNode.setAttribute("name", "Incredible Prime Root");
    	RootNode.setAttribute("version", "1.0.1");
		xmlDocument.appendChild(RootNode);
		
		InputsNode = xmlDocument.createElement("Inputs");
		RootNode.appendChild(InputsNode);
		
		OutputsNode = xmlDocument.createElement("Outputs");
		RootNode.appendChild(OutputsNode);
		
		ErrorsMessage = xmlDocument.createElement("Errors");
		RootNode.appendChild(ErrorsMessage);
		
		SetUpInputNodes();		
    }
    /**
     * Populate the Inputs Node of the XML document
     * @see #InputsNode
     * @see #AddNode
     */
    private void SetUpInputNodes()
    {
    	AddNode(InputsNode, "filePath", this.xmlFilePath);
		AddNode(InputsNode,"prime",Double.toString(this.Prime));
		AddNode(InputsNode, "integral_degree", Integer.toString(this.Integral_Degree_Root));
		AddNode(InputsNode, "fooplot_url", "http://fooplot.com/x^" + Integer.toString(this.Integral_Degree_Root)
				+ "-" + Double.toString(this.Prime));
    }
    /**
     * append children nodes of a given node [ParentNode].
     * <pre>
     * {@code 
     * <ParentNode>
     * <Tag>Text</Tag>
     * </ParentNode>}
     * </pre>
     * @param ParentNode the XML Node where a new child node will be appended.
     * @param Tag represents the tag of the XML node [Element].
     * @param Text represents the text contents of the XML node [Element]
     * @see #xmlDocument
     * @see #SetUpInputNodes()
     * @see #AddOutputNodes(String, String)
     */
    private void AddNode(Element ParentNode, String Tag, String Text)
    {
    	Element CurrentNode;
    	CurrentNode = xmlDocument.createElement(Tag);
    	CurrentNode.setTextContent(Text);
    	ParentNode.appendChild(CurrentNode);
    }
    /**
     * append outputs of the program to the XML document under parent node OutputsNode.
     * @param Tag represents the tag of the newly added XML node.
     * @param Text represents the text contents of the newly added XML node.
     * <p> Usage: this function is to be called repeatedly throughout the duration
     * of the program.
     * @see OutputsNode
     * @see AddNode(Element, String, String)
     */
    public void AddOutputNodes(String Text) 
    {
    	AddNode(OutputsNode, "Output", Text);
    }
    

    /**
     * Transforms the populated XML document into either an XML file saved at the specified path or
     * an HTML file through XSLT style sheet located at "src\IPRxml.xsl"
     * @param XMLorHTML sets the mode of transformation; 1 => performs XML transformation and writes an XML file.
     * 2 => performs XSLT transformation and writes an HTML file.
     * <p> Usage: right before the program terminates, Call WriteFile([1 or 2]); Or Both WriteFile(1);WriteFile(2) <= produces 
     * XML and HTML files.
     */
    public boolean WriteFile(int XMLorHTML) {
    	String OutputsFileName = this.xmlFilePath;
    	
    	TransformerFactory TransFactory = TransformerFactory.newInstance();
    	Transformer Trans = null;
    	
    	 try {
    	switch (XMLorHTML) {
    	  case 1:
    		  Trans = TransFactory.newTransformer();
    		  Trans.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"IPR.dtd");
    		  break;
    	  case 2:
    		  Trans = TransFactory.newTransformer(new StreamSource("src\\IPRxml.xsl"));
    		  OutputsFileName = OutputsFileName.replaceAll("(?i)xml", "html");
    		  break;
    	}
    	
    	Trans.transform(new DOMSource(xmlDocument), new StreamResult
    			(new FileOutputStream(OutputsFileName)));
    	
    	
    	} 
    	 catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
    	 
    	 return true;
    }
     
}

