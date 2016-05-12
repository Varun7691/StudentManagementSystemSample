package varun.com.studentmanagementsystemsample.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by VarunBarve on 12/05/2016.
 */
public class MySocketFactory extends SSLSocketFactory {

    SSLContext context;
    SSLSocketFactory factory;

    public MySocketFactory() {
        try {
            init();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    void init() throws KeyManagementException, NoSuchAlgorithmException {
        context = SSLContext.getInstance("SSL");
        context.init(null, new X509TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new SecureRandom());
        factory = context.getSocketFactory();
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return factory.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return factory.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        Socket socket = factory.createSocket(s, host, port, autoClose);
        setEnabledCipherSuites(socket);
        return socket;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        Socket socket = factory.createSocket(host, port);
        setEnabledCipherSuites(socket);
        return socket;
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        Socket socket = factory.createSocket(host, port, localHost, localPort);
        setEnabledCipherSuites(socket);
        return socket;
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        Socket socket = factory.createSocket(host, port);
        setEnabledCipherSuites(socket);
        return socket;
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        Socket socket = factory.createSocket(address, port, localAddress, localPort);
        setEnabledCipherSuites(socket);
        return socket;
    }

    void setEnabledCipherSuites(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sslsocket = (SSLSocket) socket;
            sslsocket.setEnabledCipherSuites(
                    getSupportedCipherSuites()
//
//                    new String[]{
//                    "SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA",
//                    "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
//                    "SSL_DHE_DSS_WITH_DES_CBC_SHA",
//                    "SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA",
//                    "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
//                    "SSL_DHE_RSA_WITH_DES_CBC_SHA",
//                    "SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA",
//                    "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5",
//                    "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA",
//                    "SSL_DH_anon_WITH_DES_CBC_SHA",
//                    "SSL_DH_anon_WITH_RC4_128_MD5",
//                    "SSL_RSA_EXPORT_WITH_DES40_CBC_SHA",
//                    "SSL_RSA_EXPORT_WITH_RC4_40_MD5",
//                    "SSL_RSA_WITH_3DES_EDE_CBC_SHA",
//                    "SSL_RSA_WITH_DES_CBC_SHA",
//                    "SSL_RSA_WITH_NULL_MD5",
//                    "SSL_RSA_WITH_NULL_SHA",
//                    "SSL_RSA_WITH_RC4_128_MD5",
//                    "SSL_RSA_WITH_RC4_128_SHA",
//                    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
////                    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
////                    "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256",
//                    "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
////                    "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256",
////                    "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384",
//                    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
////                    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
////                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
////                    "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
////                    "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384",
//                    "TLS_DH_anon_WITH_AES_128_CBC_SHA",
////                    "TLS_DH_anon_WITH_AES_128_CBC_SHA256",
////                    "TLS_DH_anon_WITH_AES_128_GCM_SHA256",
//                    "TLS_DH_anon_WITH_AES_256_CBC_SHA",
////                    "TLS_DH_anon_WITH_AES_256_CBC_SHA256",
////                    "TLS_DH_anon_WITH_AES_256_GCM_SHA384",
//                    "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
//                    "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
////                    "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
////                    "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
////                    "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
////                    "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
//                    "TLS_ECDHE_ECDSA_WITH_NULL_SHA",
//                    "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
//                    "TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA",
//                    "TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA",
//                    "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
//                    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
////                    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
////                    "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
////                    "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
////                    "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
//                    "TLS_ECDHE_RSA_WITH_NULL_SHA",
//                    "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
//                    "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
////                    "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
////                    "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
////                    "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384",
////                    "TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384",
//                    "TLS_ECDH_ECDSA_WITH_NULL_SHA",
//                    "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
//                    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
////                    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
////                    "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
////                    "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384",
////                    "TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384",
//                    "TLS_ECDH_RSA_WITH_NULL_SHA",
//                    "TLS_ECDH_RSA_WITH_RC4_128_SHA",
//                    "TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA",
//                    "TLS_ECDH_anon_WITH_AES_128_CBC_SHA",
//                    "TLS_ECDH_anon_WITH_AES_256_CBC_SHA",
//                    "TLS_ECDH_anon_WITH_NULL_SHA",
//                    "TLS_ECDH_anon_WITH_RC4_128_SHA",
//                    "TLS_EMPTY_RENEGOTIATION_INFO_SCSV",
//                    "TLS_FALLBACK_SCSV",
//                    "TLS_PSK_WITH_3DES_EDE_CBC_SHA",
//                    "TLS_PSK_WITH_AES_128_CBC_SHA",
//                    "TLS_PSK_WITH_AES_256_CBC_SHA",
//                    "TLS_PSK_WITH_RC4_128_SHA",
//                    "TLS_RSA_WITH_AES_128_CBC_SHA",
////                    "TLS_RSA_WITH_AES_128_CBC_SHA256",
////                    "TLS_RSA_WITH_AES_128_GCM_SHA256",
//                    "TLS_RSA_WITH_AES_256_CBC_SHA",
////                    "TLS_RSA_WITH_AES_256_CBC_SHA256",
////                    "TLS_RSA_WITH_AES_256_GCM_SHA384",
////                    "TLS_RSA_WITH_NULL_SHA256"
//
//            }
            );
        }
    }
}