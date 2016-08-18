package com.bluejeans.tomcat.redissessions;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class SessionSerializationMetadata implements Serializable {

    private static final long serialVersionUID = -721817928865056243L;
    private byte[] sessionAttributesHash;

    public SessionSerializationMetadata() {
        this.sessionAttributesHash = new byte[0];
    }

    public byte[] getSessionAttributesHash() {
        return sessionAttributesHash;
    }

    public void setSessionAttributesHash(final byte[] sessionAttributesHash) {
        this.sessionAttributesHash = sessionAttributesHash;
    }

    public void copyFieldsFrom(final SessionSerializationMetadata metadata) {
        this.setSessionAttributesHash(metadata.getSessionAttributesHash());
    }

    private void writeObject(final java.io.ObjectOutputStream out) throws IOException {
        out.writeInt(sessionAttributesHash.length);
        out.write(this.sessionAttributesHash);
    }

    private void readObject(final java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        final int hashLength = in.readInt();
        final byte[] sessionAttributesHash = new byte[hashLength];
        in.read(sessionAttributesHash, 0, hashLength);
        this.sessionAttributesHash = sessionAttributesHash;
    }

    private void readObjectNoData() throws ObjectStreamException {
        this.sessionAttributesHash = new byte[0];
    }

}
