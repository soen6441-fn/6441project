<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0">
<xsl:template match="/">
<html>
<head>
<xsl:comment>Style elements for the HTML Contents</xsl:comment>
<style>
#header {
    margin: 0px;
    padding: 1em 2em 1.5em 2em;
	background: #531717;
	border: 0px;
    border-bottom: 1px solid black;
	color: white;
	font-size: large;
}
#footer {
    margin: 0px;
    padding: 1em 1em 1em 1em;
	background: #531717;
	border: 0px;
    border-bottom: 1px solid black;
	color: white;
	font-size: 12px;
	margin-top: 50px;
	text-align: center;
}
#error {
     border-width: 1px;
     border-color: #531717;
     border-style: dashed;
     width: 70%;
     height: 200px;
     font-size: 12px;
	 margin-top: 30px;
	 margin-left:20px;
}	
#Sections_Titles {
	color: #727E0A;
	text-align: left;
	font-size:large;
	font-weight:bold;
	margin-top:20px;
}
#Contents {
	margin-left:70px;
	margin-top:70px;
}
#Contents ul{
	color: #531717
}
#Tbl {
	font-size:12px;
	color:black;
	border-width: 1px;
	border-color: #531717;
	border-collapse: collapse;
	margin-top:20px;
	margin-left: 20px;
}
#Tbl th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #531717;
	background-color: B0A372;
}
#Tbl td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #531717;
	background-color: #ffffff;
}
body {
	margin: 0px;
	background-color: white;
	font-family: Verdana;
	color: black;
}
a:link {
	COLOR: #531717;
	text-decoration: none;
}
a:visited {
	COLOR: #800080;
}
a:hover {
	COLOR: #727E0A;
}
a:active {
	COLOR: #00FF00;
}
</style>
<xsl:comment>Sets the Title of the Page. </xsl:comment>
<title> IPR Outputs (SOEN6441) </title>
</head>
<body>
<xsl:comment>Prints out the header with the Page Title. </xsl:comment>
<div id="header">
IPR (Incredible Prime Root) / Team E
</div>

<div id="Contents">
<xsl:comment>Lists Point of Contacts / Mailto Links added to each Contact </xsl:comment>
 <div id="Sections_Titles">
 Contacts
</div>
<ul>
<li>
 <a href="mailto:cre@tivity.info">Feras AlJumah </a>
</li>
<li>
 <a href="mailto:NaifAlQahtani@hotmail.com"> Naif AlQahtani </a> .
</li>
</ul>
<div id="Sections_Titles">
Inputs
</div>
<xsl:comment>Prints out Inputs Table / Extract Inputs from the XML file </xsl:comment>
<table id="Tbl">
<tr>
	<th>Input</th><th>Value</th>
</tr>
<tr>
	<td>XML File Path</td><td><xsl:value-of select="IPR/Inputs/filePath"/></td>
</tr>
<tr>
	<td>Prime number</td><td><xsl:value-of select="IPR/Inputs/prime"/></td>
</tr>

<tr>
	<td>Integral Degree</td><td><xsl:value-of select="IPR/Inputs/integral_degree"/></td>
</tr>
<tr>
	<td>Fooplot Graph</td>
<td>
<a target="_blank">
<xsl:attribute name="href">
<xsl:value-of select="IPR/Inputs/fooplot_url"/>
</xsl:attribute>
Click here to See Graph [open in new window]
</a>	
</td>
</tr>
</table>

<div id="Sections_Titles">
Outputs
</div>
<xsl:comment>Prints out Outputs Table / Extract Outputs from the XML file </xsl:comment>
<table id="Tbl">
<tr>
	<th>Output</th><th>Value</th>
</tr>
 <xsl:for-each select="IPR/Outputs">
    <xsl:for-each select="*">
<tr>
      <td> <xsl:value-of select="local-name()"/></td><td> <xsl:value-of select="."/></td>
</tr>
    </xsl:for-each>  
  </xsl:for-each>
</table>
<xsl:comment>Prints out Errors Messages if any. </xsl:comment>
<div id="Sections_Titles">
Error Messages
</div>
<div id="error">
<xsl:value-of select="IPR/Errors"/>
</div>
</div>
<xsl:comment> Footer </xsl:comment>
<div id="footer">Fall 2010</div>
</body>
</html>

</xsl:template>
</xsl:stylesheet>

