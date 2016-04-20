package com.trafficdrover.folder.services;

import java.io.IOException;
import java.io.Writer;

/**
 * Utility class for escaping XML text.
 * <p>
 */
public class XMLEscapes {
	/**
	 * Write String text.
	 */
	static public void writeText(Writer out, String text) throws IOException {
		_writeText(out, text, __BODY_ENTITIES);
	}

	/**
	 * Write char[] text.
	 */
	static public void writeText(Writer out, char[] text, int start, int length) throws IOException {
		_writeText(out, text, start, length, __BODY_ENTITIES);
	}

	/**
	 * Write a string attribute
	 */
	static public void writeAttribute(Writer out, String attributeValue) throws IOException {
		_writeText(out, attributeValue, _ATTRIBUTE_ENTITIES);
	}

	/**
	 * Writes out char[] text encoded with the specified entities. Passing in
	 * the entities allows us to cover the differences between attribute and
	 * body text encoding in a single definition.
	 * 
	 * This code is duplicated below for the String version
	 * 
	 * @param text
	 * @param start
	 * @param length
	 * @param entities
	 * @throws IOException
	 */
	private static void _writeText(final Writer out, final char[] text, final int start, final int length,
			final String[] entities) throws IOException {
		final int end = start + length;

		for (int i = start; i < end; i++) {
			char ch = text[i];

			// check for US7ASCII first, as that is the most common
			if (ch <= 0x7f) {
				if (ch >= 0x3f) {
					// US7ASCII characters at ? and above never need to be
					// escaped
					out.write(ch);
				} else {
					String entity = entities[ch];

					// if we have an entity, use it., otherwise the character
					// needs no escaping
					if (entity != null)
						out.write(entity);
					else
						out.write(ch);
				}
			} else {
				// make sure we are a valid high character
				if ((ch <= '\uD7FF') || ((ch >= '\uE000')) && (ch <= '\uFFFD'))
					__writeDecRef(out, ch);
			}
		}
	}

	/**
	 * Writes out String text encoded with the specified entities. Passing in
	 * the entities allows us to cover the differences between attribute and
	 * body text encoding in a single definition.
	 * 
	 * This code is duplicated above for the char[] version
	 * 
	 * @param text
	 * @param entities
	 * @throws IOException
	 */
	private static void _writeText(final Writer out, final String text, final String[] entities) throws IOException {
		final int end = text.length();

		for (int i = 0; i < end; i++) {
			char ch = text.charAt(i);

			// check for US7ASCII first, as that is the most common
			if (ch <= 0x7f) {
				if (ch >= 0x3f) {
					// US7ASCII characters at ? and above never need to be
					// escaped
					out.write(ch);
				} else {
					String entity = entities[ch];

					// if we have an entity, use it., otherwise the character
					// needs no escaping
					if (entity != null)
						out.write(entity);
					else
						out.write(ch);
				}
			} else {
				// make sure we are a valid high character
				if ((ch <= '\uD7FF') || ((ch >= '\uE000')) && (ch <= '\uFFFD'))
					__writeDecRef(out, ch);
			}
		}
	}

	/**
	 * Writes the output as a decimal escape. This is the same size or smaller
	 * than the hex equivalent and works on versions of Netscape before 4.74.
	 * See bug #1491321.
	 * <p>
	 */
	static void __writeDecRef(final Writer out, final int ch) throws IOException {
		// Formerly used String.valueOf(). This version tests out
		// about 40% faster (and on systems where GC is going gonzo,
		// it should be massively better
		out.write("&#");

		int i = ch;
		// A code point is an integer between 0 and 1,114,111, so we need to
		// check if i is above 1000000
		// purposely doing loop unrolling below for speed
		if (i > 1000000) {
			out.write('0' + (i / 1000000));
			i = i % 1000000;
			out.write('0' + (i / 100000));
			i = i % 100000;
			out.write('0' + (i / 10000));
			i = i % 10000;
			out.write('0' + (i / 1000));
			i = i % 1000;
			out.write('0' + (i / 100));
			i = i % 100;
			out.write('0' + (i / 10));
			i = i % 10;
			out.write('0' + i);
		} else if (i > 100000) {
			out.write('0' + (i / 100000));
			i = i % 100000;
			out.write('0' + (i / 10000));
			i = i % 10000;
			out.write('0' + (i / 1000));
			i = i % 1000;
			out.write('0' + (i / 100));
			i = i % 100;
			out.write('0' + (i / 10));
			i = i % 10;
			out.write('0' + i);
		} else if (i > 10000) {
			out.write('0' + (i / 10000));
			i = i % 10000;
			out.write('0' + (i / 1000));
			i = i % 1000;
			out.write('0' + (i / 100));
			i = i % 100;
			out.write('0' + (i / 10));
			i = i % 10;
			out.write('0' + i);
		} else if (i > 1000) {
			out.write('0' + (i / 1000));
			i = i % 1000;
			out.write('0' + (i / 100));
			i = i % 100;
			out.write('0' + (i / 10));
			i = i % 10;
			out.write('0' + i);
		} else {
			out.write('0' + (i / 100));
			i = i % 100;
			out.write('0' + (i / 10));
			i = i % 10;
			out.write('0' + i);
		}

		out.write(';');
	}

	// intersection array of entities that need to be output in attributes for
	// code points 0 - 62
	// regardless of whether the output in XML/HTML or body text or attribute
	// text. In particular,
	// the less than character is not escaped. as it doesn't need to be escaped
	// in HTML attributes.
	//
	// The array covers the first 63 code points of HTML/XML.
	// By combining the the numberic character references needed for the
	// illegal HTML/XML characters with the
	// other entities needed for escaping we simplify the code and allow the
	// attribute and
	// body text escaping to share the same logic
	// for unused characters, see
	// http://www.w3.org/TR/REC-html40/sgml/sgmldecl.html
	// Shared with HTML Escapes
	static final String[] __BASE_ENTITIES = { 
			null,//			"", // invalid HTML/XML characet
//													// suppress the 0 character
//													// because IE can't display
//													// its entity correctly
			null,//			"&#1;", // invalid HTML/XML character
			null,//			"&#2;", // invalid HTML/XML character
			null,//			"&#3;", // invalid HTML/XML character
			null,//			"&#4;", // invalid HTML/XML character
			null,//			"&#5;", // invalid HTML/XML character
			null,//			"&#6;", // invalid HTML/XML character
			null,//			"&#7;", // invalid HTML/XML character
			null,//			"&#8;", // invalid HTML/XML character
			null,//			"&#9;", // invalid HTML/XML character
			null, // 10, newline
			null,//			"&#11;", // invalid HTML/XML character
			null,//			"&#12;", // invalid HTML/XML character
			null, // 13, newline
			null,//			"&#14;", // invalid HTML/XML character
			null,//			"&#15;", // invalid HTML/XML character
			null,//			"&#16;", // invalid HTML/XML character
			"&#17;", // invalid HTML/XML character
			null,//			"&#18;", // invalid HTML/XML character
			null,//			"&#19;", // invalid HTML/XML character
			null,//			"&#20;", // invalid HTML/XML character
			null,//			"&#21;", // invalid HTML/XML character
			null,//			"&#22;", // invalid HTML/XML character
			null,//			"&#23;", // invalid HTML/XML character
			null,//			"&#24;", // invalid HTML/XML character
			null,//			"&#25;", // invalid HTML/XML character
			null,//			"&#26;", // invalid HTML/XML character
			null,//			"&#27;", // invalid HTML/XML character
			null,//			"&#28;", // invalid HTML/XML character
			null,//			"&#29;", // invalid HTML/XML character
			null,//			"&#30;", // invalid HTML/XML character
			null,//			"&#31;", // invalid HTML/XML character
			null, // 32 space
			null, // 33 !
			null, // 34 "
			null, // 35 #
			null, // 36 $
			null, // 37 %
			null,//			"&amp;", // 38 &
			null, // 39 '
			null, // 40 (
			null, // 41 )
			null, // 42 *
			null, // 43 +
			null, // 44 ,
			null, // 45 -
			null, // 46 .
			null, // 47 /
			null, // 48 0
			null, // 49 1
			null, // 50 2
			null, // 51 3
			null, // 52 4
			null, // 53 5
			null, // 54 6
			null, // 55 7
			null, // 56 8
			null, // 57 9
			null, // 58 :
			null, // 59 ;
			null, // 60 <
			null, // 61 =
			null,//			"&gt;", // 62 >
	};

	// array of entities that need to be output in attributes for code points 0
	// - 62
	// Shared with HTML Escapes
	static final String[] __BODY_ENTITIES;

	// array of entities that need to be output in attributes for code points 0
	// - 62
	private static final String[] _ATTRIBUTE_ENTITIES;

	static {
		// we need to escape the less than in body text, but not the quote
		__BODY_ENTITIES = __BASE_ENTITIES.clone();
		__BODY_ENTITIES['<'] = "<";//"&lt;";

		// we also need to escape the quote in attributes
		_ATTRIBUTE_ENTITIES = __BODY_ENTITIES.clone();
		_ATTRIBUTE_ENTITIES['"'] = "\"";//"&quot;";
	}

	private XMLEscapes() {
	}
}
