
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">

        <xsl:for-each select="devices/pc-component">

            <H2>Device description</H2>

            <SPAN STYLE="font-style:italic">Origin country: </SPAN>

            <xsl:value-of select="origin-country"/><BR/>

            <SPAN STYLE="font-style:italic">Price: </SPAN>

            <xsl:value-of select="price"/><BR/>

            <SPAN STYLE="font-style:italic">Is critical: </SPAN>

            <xsl:value-of select="critical"/><BR/>

        </xsl:for-each>

    </xsl:template>
</xsl:stylesheet>