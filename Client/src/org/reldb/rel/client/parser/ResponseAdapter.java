package org.reldb.rel.client.parser;

import org.reldb.rel.client.Heading;

public class ResponseAdapter implements ResponseHandler {
	public boolean isEmitHeading() {return true;}
	public boolean isEmitHeadingTypes() {return true;}
	public void beginAttributeSpec() {}
	public void attributeName(String name) {}
	public void typeReference(String name) {}
	public void endAttributeSpec() {}
	public void beginTupleDefinition() {}
	public void endTupleDefinition() {}
	public void beginContainerDefinition() {}
	public void endContainerDefinition() {}
	public void beginHeading(String typeName) {}
	public Heading endHeading() {return null;}
	public void attributeNameInTuple(int depth, String name) {}
	public void beginScalar(int depth) {}
	public void endScalar(int depth) {}
	public void beginPossrep(String name) {}
	public void endPossrep() {}
	public void separatePossrepComponent() {}
	public void primitive(String value, boolean quoted) {}
	public void beginContainer(int depth) {}
	public void beginContainerBody(int depth, Heading heading, String typeName) {}
	public void endContainer(int depth) {}
	public void beginTuple(int depth) {}
	public void endTuple(int depth) {}
	public void beginOperatorDefinition() {}
	public void beginOperatorDefinitionParameters() {}
	public void beginOperatorParameter() {}
	public void endOperatorParameter() {}
	public void emitOperatorParameterSeparator() {}
	public void endOperatorDefinitionParameters() {}
	public void beginOperatorReturnType() {}
	public void endOperatorReturnType() {}
	public void endOperatorDefinition() {}
}
