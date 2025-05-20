package fr.nationsglory.nabot.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.nationsglory.nabot.NationsAnticheat;
import fr.nationsglory.nabot.client.checker.CheckManager;
import fr.nationsglory.nabot.common.CommonProxy;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class ClientProxy extends CommonProxy {

    private static CheckManager checkManager;

    private void initCertificate() {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream stream = NationsAnticheat.class.getResourceAsStream("/isrgrootx1.pem");
            Certificate cert = cf.generateCertificate(stream);
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ISRG", cert);
            TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustFactory.init(keyStore);
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, trustFactory.getTrustManagers(), null);
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());

        } catch (CertificateException | IOException | KeyStoreException | NoSuchAlgorithmException |
                 KeyManagementException error) {
            error.printStackTrace();
        }
    }

    public void onPreInit(FMLPreInitializationEvent event) {
        initCertificate();
        checkManager = new CheckManager();
    }
}