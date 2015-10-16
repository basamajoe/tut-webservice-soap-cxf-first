package org.countrycurrency.ws.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.countrycurrency.ws.type.Validation;
import org.w3c.dom.NodeList;

public class ValidationHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String USER = "user";
	private static final String PASSWORD = "password";

	private static Map<String, String> validUsers = null;

	static {
		validUsers = new HashMap<String, String>();
		validUsers.put("user", "pass");
		validUsers.put("javi", "javipass");
		validUsers.put("jose", "josepass");
	}

	//@SuppressWarnings("unchecked")
	public boolean handleMessage(SOAPMessageContext context) {

		Boolean isOutboundMessage = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		System.out.println("handleMessage(): isOutboundMessage? " + isOutboundMessage);

		try {
			if (!isOutboundMessage) {
				// Inbound message
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				if (soapHeader == null) {
					System.out.println("SOAP Header is null");
					generateSOAPErrMessage(soapMsg, "Authorization denied, no credentials found.");
				}

				Validation val = extractValidationFromRequest(soapHeader);

				System.out.println("Val:" + val);

				if (validUsers.containsKey(val.getUser()) == false) {
					generateSOAPErrMessage(soapMsg, "Usuario no v�lido.");
				}

				String savedPassword = validUsers.get(val.getUser());
				if (savedPassword.equals(val.getPassword()) == false) {
					generateSOAPErrMessage(soapMsg, "La contrase�a no es valida.");
				}

				/*
				 * if (user.equals("jose") && pass.equals("pjose")){ return
				 * true; } else {
				 * 
				 * }
				 */

				/*
				 * Otra opcion para leer atributos
				 * 
				 * if (it.hasNext()) { Node validationNode = it.next(); Element
				 * nElement = (Element) validationNode; String user =
				 * nElement.getElementsByTagName
				 * ("user").item(0).getTextContent(); String pass =
				 * nElement.getElementsByTagName
				 * ("password").item(0).getTextContent();
				 * 
				 * System.out.println("Header validation, name: " + name +
				 * " value: " + value); }
				 */

			} else {
				// Outbound message
				// Do nothing
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	public Set<QName> getHeaders() {
		System.out.println("getHeaders()...");
		return null;
	}

	public void close(MessageContext arg0) {
		System.out.println("close()...");
	}

	public boolean handleFault(SOAPMessageContext context) {

		return true;
	}

	private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
		try {
			SOAPBody soapBody = msg.getSOAPBody();
			SOAPFault soapFault = soapBody.addFault();
			soapFault.setFaultString(reason);
			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
		}
	}

	@SuppressWarnings("null")
	private Validation extractValidationFromRequest(SOAPHeader soapHeader) {
		Validation val = null;
		String user = "";
		String pass = "";

		QName qname = new QName("http://ws.countrycurrency.org/", "validation");

		@SuppressWarnings("unchecked")
		Iterator<Node> it = soapHeader.getChildElements(qname);
		System.out.println("In Handler");

		if (it.hasNext()) {
			NodeList nodeList = it.next().getChildNodes();
			if (nodeList != null && nodeList.getLength() > 0) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					org.w3c.dom.Node node = nodeList.item(i);
					if (USER.equals(node.getNodeName())) {
						user = node.getTextContent();
					} else if (PASSWORD.equals(node.getNodeName())) {
						pass = node.getTextContent();
					}
				}
			}
		}

		if (user != null || !(user.isEmpty()) && (pass != null || !(pass.isEmpty()))) {
			val = new Validation();
			val.setUser(user);
			val.setPassword(pass);
		}
		return val;
	}
}
