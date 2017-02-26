package com.zlht.util;

import java.io.FileInputStream;
import java.security.PrivateKey;
import java.util.Date;

import cfca.internal.tool.Mechanism_Inside;
import cfca.util.CertUtil;
import cfca.util.EnvelopeUtil;
import cfca.util.KeyUtil;
import cfca.util.cipher.lib.JCrypto;
import cfca.util.cipher.lib.Session;
import cfca.x509.certificate.X509Cert;

public class TestMain{
    
    public static Session session = null;
        
    
    public static void main(String[] args) throws Exception{
//
//        String content ="{" +
//		        "\"code\":\"person_bankCard\","+
//		        "\"ddl\":\"CREATE TABLE bus_person_bankCard ( id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',card_name varchar(255) NOT NULL COMMENT '银行卡名称',card_prefix_num varchar(22) NOT NULL COMMENT '卡号前几位',area_info varchar(255) NOT NULL COMMENT '区域信息',service_phone varchar(22) NOT NULL COMMENT '服务电话',bank_name varchar(255) NOT NULL COMMENT '银行名称',card_type varchar(255) NOT NULL COMMENT '银行卡类型',card_no varchar(255) NOT NULL COMMENT '银行卡号',bank_url varchar(255) NOT NULL COMMENT '银行网址',create_date datetime DEFAULT NULL COMMENT '创建时间',update_date datetime DEFAULT NULL COMMENT '更新时间',del_flag char(1) DEFAULT NULL COMMENT '删除标记',UNIQUE KEY card_no (card_no),PRIMARY KEY (id));\""+
//		    "}";
//        
//        System.out.println("输入的原文:" + content);
//        //加密
//      //公钥证书
//        String cerPath = "D:\\1484546013722.cer";
//        String envstr=envelopMessage(content,"1",cerPath);
        
       String envstr  = "\"unionpayCon\":\"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\",\"household_info\":\"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\",\"onlinetimeByPhone\":\"MIICHAYJKoZIhvcNAQcDoIICDTCCAgkCAQIxggEwMIIBLAIBAoAU0H2iFTofMAzfFmLinjGCiCfgi+YwDQYJKoZIhvcNAQEBBQAEggEAmAmqSIwa9X6ozI1F4r+iulq5ARdgMZ6bcj0vC4EjUl7bFVjZP+GoVBCMkebjxow5cD1/dmYneUzSkkax+XNjT4Ks6dsPefoPpHFdj6dpEDx/8/zWllI/kDNkT16corKqZXf3Qyf5XETjWiEHbdKw5QjBwfm68Eqh+vplD8qD/lGHmdtwN6bK+pZiShCP0zYHwjbMQ7taD7h6QtfcBZ46TOghgo6DCvcy/NwncreDQg1QHexMudSi1VSqMAv2zDZGl8VEtKKngLqJEsfhVHcGQjNSgF1murTohlCcZ/c6rN6bklzh94ZCi2SjWT62d717RoW8YJGJ/volT29jsYpQSjCBzwYJKoZIhvcNAQcBMAwGCCqGSIb3DQMEBQCAgbM/FwyBsQkygHe2gEPdF0+Kchmw4hVJPykMPLYdAzUp5+c6HAs+PvPtjP+WLRzZy+OBIo5q11THu0fok/G+utETyTQNKITNIi8f0wmZQYywZfauuGz6BA+dENr0gXkBXc4mcnyUhnZY/UslnhKQIIi4oymtg6g2rRCAlsKv67pP/WLNhSZOjiemSQAKkQN85MsUUAii/yvzHXCAZ91/+MbGXSWjYa264iKoP/O/ehVf9bOxNw==\"";

        envstr = "{\"unionpayCon\":\"MIIRYwYJKoZIhvcNAQcDoIIRVDCCEVACAQIxggEwMIIBLAIBAoAU0H2iFTofMAzfFmLinjGCiCfgi+YwDQYJKoZIhvcNAQEBBQAEggEAmFQOGKzNvkmoZKuoUnf2j2IwVtm1xZliwtx9Z7UhRNH8lV4hUqjTf8gqrc42vBoc05O5GfqWJILLypkh6iJSq0tUyzt26pAmMJBtJDJrimAxO8OSGaDbel4lrrGgQh2iQEto08T3Wt4Cc9V0JiRtmhbdUtvJgC4FUzXsQXddsX++aQ96gB9694+osNk9vXLZX4P/MtNDehaE5kQUjyYu4hrUbxZtAUi0wLgCOQhqsWKdR2g+TohvVcWAB3lLO/PQVCm4bqnotzrlvjwFMx+x5hcVOX+zikOWQZuLUalkF0bmhFpCMl76CVoFxeUopKWQPnVWQHMN4nyvPy3A8L5lCDCCEBUGCSqGSIb3DQEHATAMBggqhkiG9w0DBAUAgIIP+CJSsq6yw2kShXGJ1cQJQR7TXixOPI78ShbH5Yhnd5ImyMPETToyHK7rGF55AADo+BJ7cBoZVE3fLg+3QU3Jdlv2DKhgOJ0l2kIQs5uhv1GwZlpzu4mdoscL9CEI062pHzIbOK5BHsFojysK21i2VUa0f/mDbJJ+ilXZYv0atex46CgxOxk47IK9tLJtGNajIKUUD545Q2PuBVdbjgti4CWUNtM2D14P1uHET1D2XUhsKa6F6yicaov8zL6kqNjcegT4zoKMklP5lXuGToa7agYGfqgEeFeWwQNbyw4T+wwCTaDCRsH3OrkIE+xJmracUTSjNnRe92DYmNfBVAQPIwWrmpSJl3MDZn+zE7kId14rbMxSlKSIkNUUoSB8tbvuY5NuiWUlfclTmJIIKEN+GPZd7jULw0g/SnZyuUeEnG+/dmClZZ4F0iWB06bnmEpKZc1SnrqbivTTXfGRUE9bH5MlkuKq2oed6+TG5dHE3snYBzNtxgUphWT+uLWidbCZQ4UoGW7DswBwxhnFmLpZ2Gn6aDNKGIrPLJhXCrss0ULIZA92dexmnibqHGMo04Ucv1yB3QxvW5ghADGtSOZYmH11g4XC9i2rkHCS07HD9nQK9g/Wy7ItzbPkQpMCfsx0LkVm8pecrog94WR1ZuUBO508jXTr6AG6Ki9T3G2CwUGXgvX4jwtij/gv3D8arjOKT0qBKRhNjf0jatGiJjbCMxm2C0xXoJ/T6u+n/K7rlwwtxCkBc0+vYuYXXYEvvv/t3HoME1bqu5JcdYnMebTtg75wCYOgQNd+6rEYNbed2GdHX/7D0zKHVRoPuhtrPmBf0pZsrT292OxNtpCdkswAJoWUxmXJRKxKGpDTqyicfhODLrj3uXZ4E1EL5LToJiJpvb6Ql66uf+5uBV+z1wfzN8WHkA30tTs3e8MKFxvA5alN9ldRMd8CxObziT7xxRrVr0mA7+Zq06QtU0QHYCA49n39xIRYebYSHwVLB41hUVHwyd/2tFntWec7jXMJ5sjiLRP6XH8A1QQlf3mxGjms9QZ7C4hPK2IQBZ7myX8hqkyUTU0ik6LPqxNIdAd69O9Hm1MYoVWN1gXnb43csYvGsYHrvJiOMFv+Lt5YVchbl9YSAzbt5CMqvI9SzK0do28lbx64H2EgZR+q+lcDpBTUIPMb0eUDiw2Fdz1GWyFNpHeafEsBfJ1Lk84/ixBGe6D/KGbZeDNG4t9xvYWSiukxSVpTiSfgZqeYBnnyt3XJLWceLxLCmJ6Gem5aDOmrNP8F7kDEoqvKFxI+vvWHKVN9Ehs1+PABSg15WY0jUo3untOTA6xobin+z2iJ8+jYClotucnUQ+LJfJTFh1HAoDbSAHT36X2h4LVGvipdv6psIcsqG79w62OITFb1T3hSRrSrZ8Ah+c5GgJCoXQIbUKaoRUgVRDlM0hW4/hagUu94g5ujc+E2XJo8DuKSQKJZJw42hK0pWDXLlYZnEoK6CFKDXLUu4IgJTcHilo3mSAN5/wZ9BMdMv48ueaqZXzGpLmmYcQ4oiklkArEp9cQMgKaNOnU2ZVAHWLwxHRgRv8ZLnIENS+wUF3bWREQhW1Zf2aQrCs9i5QDW16bguSZ2j4Igk/simOWbLNHzO/k681/PiTGVWh9RLbjKfz0QI6vUUr/5PJ5+ybfyjyZgixmSu+QS8PV54JXFqi25lx/s3n9Rab1iboiwgrDNtwQDnlKg0/JszT1ckGN7rwhTEwr7WpcnFt2maFEE05++nKwJ4Q7svnUczkDAMAGshM33H5S6u9Ua/YmrPxgIDlhe5ltAZ5yZ6xyJi8el61j9zLP2J5dWX0MEu1MPnlEvJZKLEiOmzdgnBYtBi3wmzDNJzBhL//+8rn8CrSdcJEuBal53e31NnRFz5Njrwd+SNCnq/+frc9lpLt45nheL/2m3RvmkZ6ffe9OFrn7jo57OttEemTKGGxHkS9WZgzBbf+4gKZKmPw+omrUJW969UWeNiar50h9gkDI2bjEF7QVBuBsIGCNr4FGY22zeGJMxuMJyd4JINQBt1LVp0eAzZVKRAilY6c5dj36vocU9cn4MdybWoyWmIEagXMiou1v2THvSXQ+btpavwo1gPLmu14CaPbOGvGnbzHXYLtRsmCcAqSzmqu2LknHcy5yvU5JcINDjrzZehf6bCpx7cu9ytfsmh8dtHtjphr7dGBUtzpffg8F9DwPuyA01FFl4yS7JUIrW9r/juiZwrYj1azkfPu6SwXn55mOFql3IM7C07wr0OmcmHsqLKbJDCaqeglW7TWC/XKWcKnOP/eiNz/LLZf9OUvIcX7LONsF5anXHsiHMN2wDii4YttOMQ95ZSNIPdZ9CRRjd1xQMA3pvQVA9jm68OOsSlHWx25sVelchpMH8hJHdDDhBrFmp3h+rT20mrElRQOaym+WEOIVB1gYQ+7rvsyWCFMVxi9P7xrxQJsgPpazIqVH2zTPfq/byFfp0mQwCTEz9RF+OuJHhla11X+AtFgNGmNL4Ran469GdNkMozjafbHM8zbYgibgQGWAkgOEYHoW0BlGv3bCn4854QFOWZUBHeBuLQ05T3341r041EaSS7vVlzFVRPGpSifmdMpOgGT6+g+kAN/nQhihhqVgKKQiPEc6p0TtiA8a2o7Thu+Y+EfR5k1skGONkb+YMpt1SX63hFqSLxojb/kJYzYGBH01/G6qtQNdbzu5ZGBZiUevTAvJpPqKhY2oOlJAZHtXjLeNWPdGj+vh0vNto3TXuNZGCHcYSxFNGicPttD6Da9iD1FilTduRYY1Oefn/Vt0SNyKHdRIpn7UHx48yXwsVqi0/5cBNKSy3q8+V2DsomIE4hcLAB+33k4GpY37Zp2gVxyam6qySlt60JyFpt6mTafoRMatqdr27GhSE5mB4urKvqW05VdKh+mg9G7IXt3VeHNmNuGGyC73aHen3EhCy/pOZseWAbpWTUxaPBcYdyLhRtjV1SF0EOCa/BL4X9BG6z/Aqb0kmCS/zDYZYoSKstEWjnhN2c+cAJSk/o0MuqxUDgU/9YOgJEgqB9UBsX/qbcH7XWfo+iHjoVqtRZV/7+iLT/3LjTH9d638ev7ZkAjXd5sBWB6ZmAigd6+RtAR97I31LmIcW0xii/QIFsA5LVc9zJGyKby3y5oYpfjIYjV36cqJs/BQIY+LNe71aX3znPub9nPNxDRsxZBrLX/SwlwRi+r4TyvCE650c2LAP2V7MIlsRMVlJsT8o71SPZQh2QT+Byx8Fbr9Y21G8RBzhWqSswpEEXbErh1akx0xNNJCb0cNVQ2djKtANtjG/vCjVWlJmQHoTZP5esfmdFS2kQrC0ItJ8I+iPMZtfg1Zp5bzw8Y3FX/zw/0fDxTHZlmeI2BFWq0GtQHiPvWgdnDA/NW3ZK8uWFh1mVTXLvnQSYRir8olJm7okglnqayRkZ3DNOBM3CMRiSR+Phfv8V5S+SoWvGn+GQb//Rk770xHAs/39b5zXqBRKUvMwgLOyxOTgEAkFQPZmguxNTskzwWTh1PvlctPwroBtfml76PdEcbGFZ9yRl7RBO0VqGt4GA7GhwjPnWzK/aOTEL7v5PoMEINYITIcCOz74ll8QGcZ0/+Hi4y0m0Ow8x7q0pb3Ld48NVaRtCb825ixQDZAdUNA8UU8da39NgK3zWf1VXSYsRLLcuIEj/T7Mj22ZYKFb9MXJB8l+KMjkHE6TCA/w696WC5c1jAoH6whqWErUFcEd1MniNQRmRvAjFBXto23cXOE5wpf+cC4t0gXAur2piRrhR26r+OXBndjxZcPwV9JPP4hREPf7Zavpu6JNDBpl5BmTqBXqqT2ahzWl/pe06CNq6Q6dwAt9LqsExRNl1N5PwDO70gJuwCWjXICtCt+q4RAJ1fxPnoE4eOyvCUx3Jd987wADR68PZpIc/cDnWmY1KI7j+GI1Mw+abZW5Wr8a6sXiXNthGW/e9hGxiKx5MTjctpXNry4ToerX8U+gvW/yGdGKjM6S2HYpi/OGs6cJ+2iuMSI9zYcK1/mRUXkYOc548iAsf44UgRBztD87pPL62sA/d/MBZxCy+t5Kldyk5GNaWDH+gOqrNsqktSSAXVzki+2JetbKbaKsE0Jaea06qSrRHFkRhXCwaQjtx6ZQj5r/ChMglJaEpVJCwnDqZpT+O0hywXYsg0gq41OCeX0NLReqsQC+PsnX7lU0MSgA8G3q8iwpW78AxS/x3w+4fQMsE+g5fKe/Nedc4W8Nh1b766MkgEprMaLfrog1ujVajnDs94y2NIxbebhlOxWwCKoil55N4iau5iEfdDo3Y4RxdVPDEXgmqVGMs5xYR914uXGuOC7g08lecVMD9cBgVcRnEBYg3CACK1JwtW/PQk1KT6iUEIrzBOjpBdlSkEKOVYaa6bQyL15DtSf9EVJUZoU4FIfYzgfxJb83FBnq5qI2LLmTBLFEQbPPCFGV7eGtmfhknw2huwbuJpzcz6Gv1J/JAzwSxV4+aLrvClEQncAtE3bd9ksmTrqW1th8+11crm3btVO0fTWG/0dCX5fIQa/ewO8yAYXT7qriBop3l5iO6K3RZT9VErvMm2LhdeG+Rizucd2s80dDCejRotNfSX5BiVrTth/FGuYmaVs8OxS5Ku6BcRZjgRWoaYBFKLiiAHqYrXiiajAC9Q8Ekq5bpKEunJcDmMTOzhOEaQAfiMOuXgmxh0ROdBZBFhKyzSDfcv901FLGwkx1RbKLiAMexjC3jMWa7yCtidIMAAnrLftXElqzF/XjQZ2jcVq1KtVgDJ0ELSxCF7irYmdRbxaR3Akc4WpKOUSeV8WS9Stg0H3DBOekYsKx5bCC6JKKmceLBUtN1XuY4z515FeFNCrWh0jkYvQcOexbM9EV6cli8d4EVDKazqTgjfn9+A2bbwzhDgyBtjP27y1gpPxJsoJSdZQ2YuNAYMmDdWhQubrD8qKYMBbKpcOAx0EAs6WNtuRqU8bTtLd4wEMUj3vwwGPugl8GS1yJTVcUUw2BhUARqP1SFGqwyNtwDCFNWYTJ+7y3x0j9TDTsL3oW/ii7IB+EDUQzeugBIZHDCro7HeGdq3Lgok+2txszeDI4WS++rI9ZC8hgXTv1UbGu3pov7/ATQfZmYwfZ3OUpQ79lpG5cLHMtGW3pOR0Z2Kiv6+1UMC9xg+CZ4RaHCqhSDshgZWiCZ77hBt+r/0sD0pyDgujj9JK0joKMDL8uQ0Y2nMYrKaG7DQlhpUOSUG1+CUqbc33ShIEIDIFlejt/khTH6yxf3Q88M+hyTb7rQK3ZIgRmR0uDIyqiFHJCKXnTvmMO6lRe7/Uoo9w03TEd3syNfiVNpQCmGYbJxs/J3Tca1D8A2J6qNiItN3ZzjvWBSWnaLW45ShiUVboc15elzhXoIK0HXrGHnkcLEH0q48do2/R3SiZ4EWZC0a5okiodHgRpe1fOgZR/\",\"household_info\":\"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\",\"onlinetimeByPhone\":\"MIICHAYJKoZIhvcNAQcDoIICDTCCAgkCAQIxggEwMIIBLAIBAoAU0H2iFTofMAzfFmLinjGCiCfgi+YwDQYJKoZIhvcNAQEBBQAEggEAmAmqSIwa9X6ozI1F4r+iulq5ARdgMZ6bcj0vC4EjUl7bFVjZP+GoVBCMkebjxow5cD1/dmYneUzSkkax+XNjT4Ks6dsPefoPpHFdj6dpEDx/8/zWllI/kDNkT16corKqZXf3Qyf5XETjWiEHbdKw5QjBwfm68Eqh+vplD8qD/lGHmdtwN6bK+pZiShCP0zYHwjbMQ7taD7h6QtfcBZ46TOghgo6DCvcy/NwncreDQg1QHexMudSi1VSqMAv2zDZGl8VEtKKngLqJEsfhVHcGQjNSgF1murTohlCcZ/c6rN6bklzh94ZCi2SjWT62d717RoW8YJGJ/volT29jsYpQSjCBzwYJKoZIhvcNAQcBMAwGCCqGSIb3DQMEBQCAgbM/FwyBsQkygHe2gEPdF0+Kchmw4hVJPykMPLYdAzUp5+c6HAs+PvPtjP+WLRzZy+OBIo5q11THu0fok/G+utETyTQNKITNIi8f0wmZQYywZfauuGz6BA+dENr0gXkBXc4mcnyUhnZY/UslnhKQIIi4oymtg6g2rRCAlsKv67pP/WLNhSZOjiemSQAKkQN85MsUUAii/yvzHXCAZ91/+MbGXSWjYa264iKoP/O/ehVf9bOxNw==\"}";

       System.out.println("-2:"+DateUtil.formatDateTime(new Date()));
        System.out.println("密文1:" + envstr);
        System.out.println("-1:"+DateUtil.formatDateTime(new Date()));
        //解密
        String pfxPath = "D:/git/zlht/WebRoot/1484546037548.pfx";
        System.out.println("0:"+DateUtil.formatDateTime(new Date()));
        String[] strArr = envstr.replaceAll("\"","").replaceAll("}","").split(",");
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("{");
        for(int i=0;i<strArr.length;i++){
            stringBuilder.append("\""+strArr[i].split(":")[0]+"\":");
            stringBuilder.append(openMessage(strArr[i].split(":")[1],"1",pfxPath));
            if(i==strArr.length-1){
                stringBuilder.append("}");
            }else{
                stringBuilder.append(",");
            }
        }
//        String openstr = openMessage(envstr,"1",pfxPath);
        System.out.println("解密出的原文1:"+stringBuilder.toString());
    }
    
    /**
     * 7.1 PKCS#7消息加密(数字信封)
     * 
     * @throws Exception
     */
    @SuppressWarnings("unused")
	private static String envelopMessage(String content,String encryptTypeFlag,String cerPath) throws Exception{
        
        String[] certPaths = cerPath.split(";");
        X509Cert[] certs = new X509Cert[certPaths.length];
        for(int i = 0; i < certPaths.length; i++){
            X509Cert cert = new X509Cert(new FileInputStream(certPaths[i]));
            certs[i] = cert;
        }
        String symmetricAlgorithm = getSymmetricAlgorithm(encryptTypeFlag);
        
        byte[] encryptedData = EnvelopeUtil.envelopeMessage(content.getBytes("UTF8"), symmetricAlgorithm, certs);
        
        return new String(encryptedData);
    }
    
    /**
     * 选择 加密/解密 算法
     * 
     * @param encryptTypeFlag
     * 				1 RC4
     * 				2 DESede/ECB/PKCS7Padding
     * 				3 DESede/CBC/PKCS7Padding
     * 
     * @return
     * @throws Exception
     */
    private static String getSymmetricAlgorithm(String encryptTypeFlag) throws Exception{
        String symmetricAlgorithm = null;
        while(true){
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
                System.out.println("加密/解密 算法选择错误。");
                break;
            }
        }
        return symmetricAlgorithm;
    }
    
    
    /**
     * 7.1.1 消息解密(数字信封)
     * 
     * @throws Exception
     */
    private static String openMessage(String str,String pass,String pfxPath) throws Exception{

    	System.out.println("1:"+DateUtil.formatDateTime(new Date()));
    	// 软库初始化
        JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
        System.out.println("2:"+DateUtil.formatDateTime(new Date()));
        session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
        
    	String encryptedData =str;
    	//输入原证书
        
        String pwd = pass;
        System.out.println("3:"+DateUtil.formatDateTime(new Date()));
        PrivateKey priKey = (PrivateKey)KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        System.out.println("4:"+DateUtil.formatDateTime(new Date()));
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        System.out.println("5:"+DateUtil.formatDateTime(new Date()));
        
        byte[] sourceData = EnvelopeUtil.openEvelopedMessage(encryptedData.getBytes(), priKey, cert, session);
        System.out.println("6:"+DateUtil.formatDateTime(new Date()));
        return new String(sourceData, "UTF8");
    }
    
   
  
}
