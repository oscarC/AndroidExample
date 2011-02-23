
package com.AndroidExample;

import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import android.content.Context;

public class MyHttpClient extends DefaultHttpClient {
    @Override
    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", new FakeSocketFactory(), 443));
        return new SingleClientConnManager(getParams(), registry);
    }

    private Context context;

    public MyHttpClient(Context context) {
        this.context = context;
    }

    private SSLSocketFactory newSslSocketFactory() {
        try {
            KeyStore trusted = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = context.getAssets().open("kestore.bks");
            try {
                trusted.load(in, "wastinglife".toCharArray());
            } finally {
                in.close();
            }
            SSLSocketFactory factory = new SSLSocketFactory(trusted, "wastinglife");
            return factory;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

}
