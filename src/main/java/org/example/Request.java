package org.example;

class Request {
    private final ExternalRequest externalRequest;
    private final InternalRequest internalRequest;

    public Request(ExternalRequest externalRequest, InternalRequest internalRequest) {
        this.externalRequest = externalRequest;
        this.internalRequest = internalRequest;
    }

    public ExternalRequest getExternalRequest() {
        return externalRequest;
    }

    public InternalRequest getInternalRequest() {
        return internalRequest;
    }
}