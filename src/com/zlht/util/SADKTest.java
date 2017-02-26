package com.zlht.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import cfca.internal.tool.Mechanism_Inside;
import cfca.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.org.bouncycastle.asn1.x509.Extensions;
import cfca.sadk.util.license.LicenseUtil;
import cfca.sm2rsa.common.CBCParam;
import cfca.sm2rsa.common.Mechanism;
import cfca.system.SecurityContext;
import cfca.util.CertUtil;
import cfca.util.EncryptUtil;
import cfca.util.EnvelopeUtil;
import cfca.util.KeyUtil;
import cfca.util.SignatureUtil;
import cfca.util.SignatureUtil2;
import cfca.util.cipher.lib.JCrypto;
import cfca.util.cipher.lib.Session;
import cfca.x509.certificate.X509Cert;
import cfca.x509.certificate.X509CertHelper;
import cfca.x509.certificate.X509CertValidator;

public class SADKTest{

    private static final String LICENSE_FILE = null;
    public static Session session = null;


    public static void main(String[] args) throws Exception{

        // 软库初始化
        JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
        session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);


        //加密
//        String envstr=envelopMessage();
        //解密
        openMessage("envstr","1");

    }




    /**
     * 7.1 PKCS#7消息加密(数字信封)
     *
     * @throws Exception
     */
    private static String envelopMessage() throws Exception{
        System.out.println("输入接收者证书路径，多张证书用；分开");
        //公钥证书
        String cerPath = "D:/git/zlht/WebRoot/121.cer";
        String[] certPaths = cerPath.split(";");
        X509Cert[] certs = new X509Cert[certPaths.length];
        for(int i = 0; i < certPaths.length; i++){
            X509Cert cert = new X509Cert(new FileInputStream(certPaths[i]));
            certs[i] = cert;
        }
        System.out.println("输入原文:");
        String content ="haimimg";
        System.out.println("source data:" + content);
        String symmetricAlgorithm = getSymmetricAlgorithm();

        byte[] encryptedData = EnvelopeUtil.envelopeMessage(content.getBytes("UTF8"), symmetricAlgorithm, certs);

        System.out.println("envelope successed:" + new String(encryptedData));

        return new String(encryptedData);
    }
    private static String getSymmetricAlgorithm() throws Exception{
        System.out.println("选择 加密/解密 算法:");
        System.out.println("****************");
        System.out.println("1 RC4");
        System.out.println("2 DESede/ECB/PKCS7Padding");
        System.out.println("3 DESede/CBC/PKCS7Padding");
        System.out.println("****************");
        System.out.println("请输入选项:");
        String encryptTypeFlag = "";
        String symmetricAlgorithm = null;
        while(true){
            encryptTypeFlag = getInput();
            if("1".equals(encryptTypeFlag)){
                symmetricAlgorithm = Mechanism_Inside.RC4;
                break;
            }else if("2".equals(encryptTypeFlag)){
                symmetricAlgorithm = Mechanism_Inside.DES3_ECB;
                break;
            }else if("3".equals(encryptTypeFlag)){
                symmetricAlgorithm = Mechanism_Inside.DES3_CBC;
                break;
            }else{
                System.out.println("输入错误。请重新输入:");
            }
        }
        return symmetricAlgorithm;
    }


    /**
     * 获得界面输入
     *
     * @return
     * @throws Exception
     */
    private static String getInput() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        if("r".equals(input)){
            throw new Exception("终止测试");
        }
        return input;
    }




    /**
     * 7.1.1 消息解密(数字信封)
     *
     * @throws Exception
     */
    private static void openMessage(String str,String pass) throws Exception{

    	String encryptedData =str;
        str="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\",\"household_info\":\"MIIE4gYJKoZIhvcNAQcDoIIE0zCCBM8CAQIxggEwMIIBLAIBAoAU0H2iFTofMAzfFmLinjGCiCfgi+YwDQYJKoZIhvcNAQEBBQAEggEAJHn0t8X3D4JKGJc4YFlwOIAQ7peA7DgeHcuOe0uLUb5G1LJmyy66Gn1xd3zsW2VmoF/v6A7fPMNU6P7bzF/PY9XUXKv+B4/gO0LOrKZwlTUXrl1T9xsdYBym8YscOW4v7+SNTJJjPYphtLQW91TQZlXdvx2MASPAuW+UAGKBsJ/2pf+41e0VydTr34GNfPDVzT2nDupwpO7mjEqd4FuiSk26W0Dor68iCcZ2PlUGrjQjSGdlJzQ0abn0oxW2Qon7NNtNnLcZ491hfgE/bwg04ODvw2M9Bt3g9fCLFDyLm07f56abUWi6n39t39g5XJauQAKxkRJQVWnvQ9BFVaa4QjCCA5QGCSqGSIb3DQEHATAMBggqhkiG9w0DBAUAgIIDdyB4m3+G7mObxqeL6SrpiMjKp2I7szE8nHB2evEP6OQHVOFlc61ltScXrcrd9vpP7F2wGBzfZco+OtMRFFWm2lxiCgAjIrje/9GwXuJOSqHOlyQJyaqzrtU3vLZBdipGaG4sLqDGbeHa+k5PE2g1/uuORIq93RXpYEmbUmFbkHqgjzsWKdZHMJDaSmgCWzC6IJePYDTzPTZPto5uQmm0tn35nH8/7V29NEuu+ysOQq8g1gyUwD2fQRB01ynrjO93CyA7b8C3049EzHYPEWD0zJs93YeTkadTswcG3GTWTmQmLgp/dnDAP5oJMgMYpITOvyA+Y2AjSMV7fu9Ce2wzs+aQvEzcS/VrrGdZrDRnth2+Zb9kJBYPpUv8/mJ4dxHYKs6JO9JM8pJ8MrBd2ASlGSYsZ+/nZT/6Jk7n2GAKLT65XPn9CuS23rFFdtpHg+t0JPRY1CMh5cajPKN8wCxJp+NtmyRt/ejmMciKfE23YixhUQgZHNlCsZitv61l/cUdkbkzur5uFhcudUrakTsFyfUlsFQ1V8KBE8TsWul8EeI1pNSy2Agmv/oujChL+AYHg55mOzjC1T2R2Z76YyObR2bxf/KoeKnK2+iGH46bXeXyB2otDJJG9GDD48L6gpej7pkw89T+OTs9BO6JnLtiUMdYErardMcFzamX+85bw5SMcBnsa+n4ui2bxrBLX5dnJWSLKsu8Z2S/4FSsajS7A7R1P8ByN7dscXHai+pwkfhKEizcu7KUL9txUu7bEgom6abWebJSAkeCFK992bcTvFxiJ53Y5SKKgz+m45WIlOKghqZ6KWwK7vm9rK+q7lRlLylW4D7kBrZzk2nxDY+pzyy1m7IzMWamrYAC4Day13Czl+0tFHv1SxiNXyjAZFQFGpGoD6gmi23lLUxfh8OZiTefUKZUIGALlDMAHe4Qr2EGXNv2an9c0PEyT8Hi27xiGtVYvWX5qNu5clqMsMVRdSodufdfju/g/XiV+daM2GasVS/bzxNJXZgEVGWc4tLc2XvHhfhsKcDnotF2Za1qAAMNpDzWJN92X4czOF2ehu1bYwqgpIOtefEjtji2dsITO2H1+mzIYQ3D8OMQ31qUkt6F17oFfha/S5uIsDOiFNw424fDBU25e7s78zTQyIbIQv2VN/wCk5oJYCFf+3ZGeI2WwJBZrOYk\",\"onlinetimeByPhone\":\"MIIBtgYJKoZIhvcNAQcDoIIBpzCCAaMCAQIxggEwMIIBLAIBAoAU0H2iFTofMAzfFmLinjGCiCfgi+YwDQYJKoZIhvcNAQEBBQAEggEAWBLQoYM+ZDea0f7aOtd6WtEQB4Yjm1TKiyP5Pd16oyYyX7dt1JlqUdS1nO+h4JnDrEEAikxEKC2/0nLGtdjZs8ehYFTTdJH8FdTVqFf2t2SFfTGh7ikVw7bRX7in9jNk7bpGoTr0ylh+o2aHGBxbsZmf4mUXBTf5E+a2cGiNUYm7d3prpffcGX3MnVWUHAg0H6sKOcv3EX840ToZuQqSxm4TJK5eE4oMVNgOJRLYkinJa96R4wlHt27TDHc+qfvhUfImTd0z6yAL9mbuf0HFCd586YgC8DC2fJnQxFqSEk2cdmGkgORamGM3GhOzKs5GsSQIGcmX9vO+O1KIjFbdrDBqBgkqhkiG9w0BBwEwDAYIKoZIhvcNAwQFAIBPs+b5uKStJ0LffzVOu4yHiu4FoFN/2YgFQJo9kKk0bwc34ZTporPKmJydNIytKSDjJ6DocOtiK6GqpLZwcOGGsnPNqy0bOSKgeIonERz02w==";
    	//输入原证书
        String pfxPath = "D:/git/zlht/WebRoot/1484546037548.pfx";
        String pwd = pass;
        pwd = "1";
        PrivateKey priKey = (PrivateKey)KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);

        byte[] sourceData = EnvelopeUtil.openEvelopedMessage(encryptedData.getBytes(), priKey, cert, session);
        System.out.println("get the source data:" + new String(sourceData, "UTF8"));
    }



}
