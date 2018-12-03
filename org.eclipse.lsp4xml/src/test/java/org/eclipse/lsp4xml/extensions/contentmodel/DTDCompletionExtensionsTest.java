package org.eclipse.lsp4xml.extensions.contentmodel;

import static org.eclipse.lsp4xml.XMLAssert.c;
import static org.eclipse.lsp4xml.XMLAssert.te;

import org.eclipse.lsp4j.CompletionCapabilities;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemCapabilities;
import org.eclipse.lsp4xml.XMLAssert;
import org.eclipse.lsp4xml.commons.BadLocationException;
import org.eclipse.lsp4xml.services.XMLLanguageService;
import org.eclipse.lsp4xml.services.extensions.CompletionSettings;
import org.junit.Test;

public class DTDCompletionExtensionsTest {

	@Test
	public void completionInRoot() throws BadLocationException {
		// completion on <|
		String xml = "<?xml version=\"1.0\"?>\r\n" + //
				"  <!DOCTYPE catalog\r\n" + //
				"    PUBLIC \"-//OASIS//DTD Entity Resolution XML Catalog V1.0//EN\"\r\n" + //
				"           \"http://www.oasis-open.org/committees/entity/release/1.0/catalog.dtd\">\r\n" + //
				"\r\n" + //
				"  <catalog xmlns=\"urn:oasis:names:tc:entity:xmlns:xml:catalog\"\r\n" + //
				"           prefer=\"public\">\r\n" + //
				"\r\n" + //
				"    <|";
		testCompletionFor(xml,
				c("delegatePublic", te(8, 4, 8, 5, "<delegatePublic publicIdStartString=\"$1\" catalog=\"$2\" />$0"), "<delegatePublic"), //
				c("public", te(8, 4, 8, 5, "<public publicId=\"$1\" uri=\"$2\" />$0"), "<public"));
	}

	@Test
	public void completionWithChoiceAttribute() throws BadLocationException {
		// completion on <|
		String xml = "<?xml version=\"1.0\"?>\r\n" + //
				"  <!DOCTYPE catalog\r\n" + //
				"    PUBLIC \"-//OASIS//DTD Entity Resolution XML Catalog V1.0//EN\"\r\n" + //
				"           \"http://www.oasis-open.org/committees/entity/release/1.0/catalog.dtd\">\r\n" + //
				"\r\n" + //
				"  <catalog |";
		testCompletionFor(xml,
				c("prefer", te(5, 11, 5, 11, "prefer=\"${1|system,public|}\"$0"), "prefer"));
	}

	
	private void testCompletionFor(String xml, CompletionItem... expectedItems) throws BadLocationException {
		CompletionSettings completionSettings = new CompletionSettings();
		CompletionCapabilities completionCapabilities = new CompletionCapabilities();
		CompletionItemCapabilities completionItem = new CompletionItemCapabilities(true); // activate snippets
		completionCapabilities.setCompletionItem(completionItem);
		completionSettings.setCapabilities(completionCapabilities);
		XMLAssert.testCompletionFor(new XMLLanguageService(), xml, "src/test/resources/catalogs/catalog.xml", null, null, null, completionSettings, expectedItems);;
	}
}
