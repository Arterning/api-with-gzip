package com.jinchange.gzip.util;

import org.apache.commons.codec.binary.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtils {
 
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    /**
     * 字符串压缩为GZIP字节数组
     * @param str
     * @return
     */
    public static byte[] compress(String str) {
        return compress(str, GZIP_ENCODE_UTF_8);
    }
 
    /**
     * 字符串压缩为GZIP字节数组
     * @param str
     * @param encoding
     * @return
     */
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
 
    /**
     * GZIP解压缩
     * @param bytes
     * @return
     */
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
 
    /**
     * 解压并返回String
     * @param bytes
     * @return
     */
    public static String uncompressToString(byte[] bytes) throws IOException {
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static byte[] uncompressToByteArray(byte[] bytes) throws IOException {
        return uncompressToByteArray(bytes, GZIP_ENCODE_UTF_8);
    }
 
    /**
     * 解压成字符串
     * @param bytes 压缩后的字节数组
     * @param encoding 编码方式
     * @return 解压后的字符串
     */
    public static String uncompressToString(byte[] bytes, String encoding) throws IOException {
        byte[] result = uncompressToByteArray(bytes, encoding);
        return new String(result);
    }

    /**
     * 解压成字节数组
     * @param bytes
     * @param encoding
     * @return
     */
    public static byte[] uncompressToByteArray(byte[] bytes, String encoding) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("解压缩失败！");
        }
    }

    /**
     * 将字节流转换成文件
     * @param filename
     * @param data
     * @throws Exception
     */
    public static void saveFile(String filename,byte [] data)throws Exception{
        if(data != null){
            String filepath ="/Users/algorix/Downloads/" + filename;
            File file  = new File(filepath);
            if(file.exists()){
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data,0,data.length);
            fos.flush();
            fos.close();
            System.out.println(file);
        }
    }


    public static void main(String[] args) throws Exception {
        String str = "{\"adName\":\"ad1\",\"adTag\":\"<img src='https://ssb-use1.smartadserver.com:443/api/imp?callerid=40&amp;rtb=1&amp;rtbnid=3817&amp;rtbbid=922501523137776153&amp;rtbh=f0486d91c43d678d29edeea5b5e7b826b47b3d58&amp;rtblt=637839192022385241&amp;rtbet=2&amp;rtbptnid=101&amp;cftgid=bcfd9477a6a4' border='0' width='0' height='0' style='display:none'/><img src='https://ssb-use1.smartadserver.com:443/api/event?callerid=40&amp;actionid=10016&amp;rtb=1&amp;rtbnid=3817&amp;rtbbid=922501523137776153&amp;rtbh=f0486d91c43d678d29edeea5b5e7b826b47b3d58&amp;rtblt=637839192022385241&amp;rtbet=2&amp;rtbptnid=101' border='0' width='0' height='0' style='display:none'/><DIV STYLE=\\\\\\\"position: absolute; left: 0px; top: 0px; visibility: hidden;\\\\\\\"><IMG SRC=\\\\\\\"https://pagead2.googlesyndication.com/pagead/gen_204?id=xbid&dbm_b=AKAmf-BYOvUFzqHszVQzB8qfvmFA0KUKwEmzXHSMr8KUmpYxuVYLsSPkr5In0delLK4rykXCBFS4yQKrPsWrNam9150ZmaBRW1yFki70rB3w-x-zv5Wr0r4\\\\\\\" BORDER=0 WIDTH=1 HEIGHT=1 ALT=\\\\\\\"\\\\\\\" STYLE=\\\\\\\"display:none\\\\\\\"></DIV><iframe title=\\\\\\\"Blank\\\\\\\" src=\\\\\\\"https://googleads.g.doubleclick.net/xbbe/pixel?d=CNfM9gIQv_S38AIY0ua-xAEwAQ&v=APEucNW-zSyy3v1TPAmXsoISK1pk8YGMtgB9XFUJpb3z1nOO6Fy7RiKGaeOO87KPHmjvqC7U9QuksFqKOt21zm0Q9VgXylDjv64tF5w8sE2vqfi5By8kQK4\\\\\\\" style=\\\\\\\"display:none\\\\\\\" aria-hidden=\\\\\\\"true\\\\\\\"></iframe><div><div style=\\\\\\\"position:relative;\\\\\\\"><script data-jc=\\\\\\\"75\\\\\\\" data-jc-version=\\\\\\\"r20220323\\\\\\\" data-jc-flags=\\\\\\\"[&quot;x%278442&#39;921378y&quot;]\\\\\\\">(function(){/*  Copyright The Closure Library Authors. SPDX-License-Identifier: Apache-2.0 */ var k=\\\\\\\"function\\\\\\\"==typeof Object.create?Object.create:function(a){function b(){}b.prototype=a;return new b},l;if(\\\\\\\"function\\\\\\\"==typeof Object.setPrototypeOf)l=Object.setPrototypeOf;else{var m;a:{var n={a:!0},p={};try{p.__proto__=n;m=p.a;break a}catch(a){}m=!1}l=m?function(a,b){a.__proto__=b;if(a.__proto__!==b)throw new TypeError(a+\\\\\\\" is not extensible\\\\\\\");return a}:null}var q=l,r=this||self;var t=new function(a,b){this.key=a;this.defaultValue=void 0===b?0:b;this.valueType=\\\\\\\"number\\\\\\\"}(\\\\\\\"100004\\\\\\\",16E3);var u=Array.prototype.forEach?function(a,b){Array.prototype.forEach.call(a,b,void 0)}:function(a,b){for(var d=a.length,c=\\\\\\\"string\\\\\\\"===typeof a?a.split(\\\\\\\"\\\\\\\"):a,e=0;e<d;e++)e in c&&b.call(void 0,c[e],e,a)},v=Array.prototype.map?function(a,b){return Array.prototype.map.call(a,b,void 0)}:function(a,b){for(var d=a.length,c=Array(d),e=\\\\\\\"string\\\\\\\"===typeof a?a.split(\\\\\\\"\\\\\\\"):a,f=0;f<d;f++)f in e&&(c[f]=b.call(void 0,e[f],f,a));return c},w=Array.prototype.reduce?function(a,b,d){return Array.prototype.reduce.call(a,b, d)}:function(a,b,d){var c=d;u(a,function(e,f){c=b.call(void 0,c,e,f,a)});return c};function x(a){for(var b=[],d=0;d<a;d++)b[d]=\\\\\\\"\\\\\\\";return b};/*  SPDX-License-Identifier: Apache-2.0 */ function y(a){y[\\\\\\\" \\\\\\\"](a);return a}y[\\\\\\\" \\\\\\\"]=function(){};function A(a,b){if(a)for(var d in a)Object.prototype.hasOwnProperty.call(a,d)&&b(a[d],d,a)}var B=/https?:\\\\\\\\/\\\\\\\\/[^\\\\\\\\/]+/;function C(a){return(a=B.exec(a))&&a[0]||\\\\\\\"\\\\\\\"};var D=0;function E(a){var b=document.currentScript;return(b=void 0===b?null:b)&&b.getAttribute(\\\\\\\"data-jc\\\\\\\")===String(a)?b:document.querySelector('[data-jc=\\\\\\\"'+a+'\\\\\\\"]')};function F(){G||(G=new H);var a=G.g[t.key];if(\\\\\\\"proto\\\\\\\"===t.valueType){try{var b=JSON.parse(a);if(Array.isArray(b))return b}catch(d){}return t.defaultValue}return typeof a===typeof t.defaultValue?a:t.defaultValue};function H(){this.g={};var a=E(D);a=a&&a.getAttribute(\\\\\\\"data-jc-flags\\\\\\\")||\\\\\\\"\\\\\\\";try{var b=JSON.parse(a)[0];a=\\\\\\\"\\\\\\\";for(var d=0;d<b.length;d++)a+=String.fromCharCode(b.charCodeAt(d)^\\\\\\\"\\\\\\\\u0003\\\\\\\\u0007\\\\\\\\u0003\\\\\\\\u0007\\\\\\\\b\\\\\\\\u0004\\\\\\\\u0004\\\\\\\\u0006\\\\\\\\u0005\\\\\\\\u0003\\\\\\\".charCodeAt(d%10));this.g=JSON.parse(a)}catch(c){}}var G,I=H;function J(){this.g={}}I.prototype=k(J.prototype);I.prototype.constructor=I; if(q)q(I,J);else for(var K in J)if(\\\\\\\"prototype\\\\\\\"!=K)if(Object.defineProperties){var L=Object.getOwnPropertyDescriptor(J,K);L&&Object.defineProperty(I,K,L)}else I[K]=J[K];I.l=J.prototype;var M=RegExp(\\\\\\\"^https?://(\\\\\\\\\\\\\\\\w|-)+\\\\\\\\\\\\\\\\.cdn\\\\\\\\\\\\\\\\.ampproject\\\\\\\\\\\\\\\\.(net|org)(\\\\\\\\\\\\\\\\?|/|$)\\\\\\\");function N(a){a=a||O();for(var b=new P(r.location.href,!1),d=null,c=a.length-1,e=c;0<=e;--e){var f=a[e];!d&&M.test(f.url)&&(d=f);if(f.url&&!f.h){b=f;break}}e=null;f=a.length&&a[c].url;0!=b.depth&&f&&(e=a[c]);return new Q(b,e,d)} function O(){var a=r,b=[],d=null;do{var c=a;try{var e;if(e=!!c&&null!=c.location.href)b:{try{y(c.foo);e=!0;break b}catch(h){}e=!1}var f=e}catch(h){f=!1}if(f){var g=c.location.href;d=c.document&&c.document.referrer||null}else g=d,d=null;b.push(new P(g||\\\\\\\"\\\\\\\"));try{a=c.parent}catch(h){a=null}}while(a&&c!=a);c=0;for(a=b.length-1;c<=a;++c)b[c].depth=a-c;c=r;if(c.location&&c.location.ancestorOrigins&&c.location.ancestorOrigins.length==b.length-1)for(a=1;a<b.length;++a)g=b[a],g.url||(g.url=c.location.ancestorOrigins[a- 1]||\\\\\\\"\\\\\\\",g.h=!0);return b}function Q(a,b,d){this.i=a;this.j=b;this.g=d}function P(a,b){this.url=a;this.h=!!b;this.depth=null};function R(a,b,d,c,e){var f=[];A(a,function(g,h){(g=S(g,b,d,c,e))&&f.push(h+\\\\\\\"=\\\\\\\"+g)});return f.join(b)}function S(a,b,d,c,e){if(null==a)return\\\\\\\"\\\\\\\";b=b||\\\\\\\"&\\\\\\\";d=d||\\\\\\\",$\\\\\\\";\\\\\\\"string\\\\\\\"==typeof d&&(d=d.split(\\\\\\\"\\\\\\\"));if(a instanceof Array){if(c=c||0,c<d.length){for(var f=[],g=0;g<a.length;g++)f.push(S(a[g],b,d,c+1,e));return f.join(d[c])}}else if(\\\\\\\"object\\\\\\\"==typeof a)return e=e||0,2>e?encodeURIComponent(R(a,b,d,c,e+1)):\\\\\\\"...\\\\\\\";return encodeURIComponent(String(a))};function T(a,b){this.g=a;this.depth=b}function U(){function a(h,z){return null==h?z:h}var b=O(),d=Math.max(b.length-1,0),c=N(b);b=c.i;var e=c.j,f=c.g,g=[];f&&g.push(new T([f.url,f.h?2:0],a(f.depth,1)));e&&e!=f&&g.push(new T([e.url,2],0));b.url&&b!=f&&g.push(new T([b.url,0],a(b.depth,d)));c=v(g,function(h,z){return g.slice(0,g.length-z)});!b.url||(f||e)&&b!=f||(e=C(b.url))&&c.push([new T([e,1],a(b.depth,d))]);c.push([]);return v(c,function(h){return V(d,h)})} function V(a,b){var d=w(b,function(e,f){return Math.max(e,f.depth)},-1),c=x(d+2);c[0]=a;u(b,function(e){return c[e.depth+1]=e.g});return c}function W(){var a=U();return v(a,function(b){return S(b)})};D=75; window.rfl=function(a){try{var b=Number(0===(F()||0)?2083:F()),d=W();d.pop();var c=b-a.length-5;for(b=0;b<d.length;b++){var e=encodeURIComponent(d[b]);if(e.length<=c)return setTimeout(function(){var f=void 0===f?.01:f;if(!(Math.random()>f)){var g=E(75);g=\\\\\\\"https://\\\\\\\"+(g&&\\\\\\\"true\\\\\\\"===g.getAttribute(\\\\\\\"data-jc-rcd\\\\\\\")?\\\\\\\"pagead2.googlesyndication-cn.com\\\\\\\":\\\\\\\"pagead2.googlesyndication.com\\\\\\\")+\\\\\\\"/pagead/gen_204?id=jca&jc=75&version=\\\\\\\";var h=(h=E(75))&&h.getAttribute(\\\\\\\"data-jc-version\\\\\\\")||\\\\\\\"unknown\\\\\\\";f=g+h+\\\\\\\"&sample=\\\\\\\"+f;g=window; if(h=g.navigator)h=g.navigator.userAgent,h=/Chrome/.test(h)&&!/Edge/.test(h)?!0:!1;h&&g.navigator.sendBeacon?g.navigator.sendBeacon(f):(g.google_image_requests||(g.google_image_requests=[]),h=g.document,h=void 0===h?document:h,h=h.createElement(\\\\\\\"img\\\\\\\"),h.src=f,g.google_image_requests.push(h))}},0),a+\\\\\\\"&rfl=\\\\\\\"+e}return a}catch(f){}return a};}).call(this);</script><script>var url = 'https://googleads.g.doubleclick.net/dbm/ad?dbm_c=AKAmf-CPXqvszy8TWIndn8EhRTKVxux6KHN_Gd1mmp_IeI_tLmNxPEseD4ZBd7bpefZYWO-HyxoIf8XnmVknlDuzbNpTbwNGGVDraSAUSrx4iS0oMYvtmOAJnXbpEVldaKfueiDpBMvFRcpJEQF_aZuS9xEGgLZxnA&dbm_d=AKAmf-A-DDotb5smAr0HLeLHOfG0TCTJmzLXcfjSj3y5Nor6D0BNBihDKzUjFDRgOm_whh7j19ttpWpn3czJUyH92qQWgozGButfAU98KKHYe1sLeYWb5BnrdOIbL_w8tIKhpLv5auLP5mkiwPQjID7Rcl9llRpiOnImZN6Zqh7vyKJsAbAzSYR11uTK_C7l5SKMO5YgvlZ0vIAgsOs6wuJB5XJ4OuqCpbUv9TRVF_7pxF-MH2GCtfVTTr4ZJm-u9n_pXGuy02OuoGKQSU_cdLEOaDt7j2vtwdTcPGawGRmobfVTFGrA08umznkKiRxqAZwwxSUaLz9YVpwe4C90s-G8rLzR7jBynDO2vvxzJXUHQXjkmHaABehBWWSBdhRndWH3WameQVkIc0lgPGv8bsU1a8c9s6mSp11hVhPUpoLx1Q7HbU0BIVTv4ZwNqcl7tMvRZMQy9gXC0ZtorDKrbAPQcuHifBTZkZ4m-70IWCDK9eFDyO6ySYh7IfPIGG9oEQjEgFlOGH6mdfwRylOOLIn8_AsWwFwk22MiOepPOM1-CvLQ1AMtd5HnBpR8EmkBVUYB-MLbsR5vzihiu9EEyijo97HXFd4NvpKdaJxUzD819523o0iTxbbOfx2VSlsDbcDh2fR7lnBBkD0KScT2vQoGKVH8mKSLmGY-Pk3miwxS8BMd2xZYxpLCSkgHCwQPVnvRnDMZQJ2PJlIDKPfwfbs8t2g8Vh_HXey6yN5C0bwjiUkT2cPSxmv4_lD_ArTziagfkmks5w2-E441RM2uqjuVEDQpJAhyknuWjt9BgC2lgMq5S0qhJIXQpmNceeWB9vt19xRl88v2CmKV5jjj90AUAis6aX9s52VW9cnWwy1spzE_Q0rIEz_nyrTCpnEi-gDRMo_dxwkHy6hQOCoh_tZmwcRDHu_j7c3fVxWwV7w8QG3qBXmJSACW5gMT5K3ne3haiiWaa40FIJJx4lng9T-8mXqXICWwxIj45vXO8dgRQ_lP3T9YcN8QSv11sR8aAyrkMEFqm-vLKB1ItidpwEHoHtbpqbpk1Ms2-BweRbsbuV5LD5oVR2OhTSnN_QbA5_nOwvGJ-WiZCO0gtKrJxl9qnY23WNu_N8jPjBVO0oGJZJRFcI-XCRfW_YOgKQvXay8g1DwDw8wsUSO4gJaoUTIV8ibjYGTxgTO8eqNFkOtehBPQF_lM9iJDSEBgQj3LomlFIShXsALP-xoOjuqlyZFuRsGJaqCTP19QnDoxyXWumuSTvl_hQbPLT7LJJ71f7U9oKKsxQ3tX_XgxxWl6sSfYCzOtRLmJOcufO0R-WmWJTspd7RLE90vxKkG_0p2foSUNZwKWjkekeh2ITkUQef-WNXSZ-PeSr741vr_ffK4e_w3HG7t1lvcKKZwHF8lMGegj25zi12RNU2KiAKL3Wl18xhqxKmhlXXASRzZBiUnUOlRICHKQ72ekbe9xTxmw5g1NgYElGtmrCdmhy9fC6UohwZ6Sbft9poyZG5RZ4RvhQgISegRznYENedvayk-6oi6MUXteTBO31pLM1zwsRRWi7FsmjL942aMTWJ-jNAAOXflaKvRL2j2FKjs61MUn7sfhV-Tpc-Hb2dEJAnDgjbSrlUb9JubVmUMaPO6iCW-deXNkeFqRDjPU4Si_z08uY_pMwIMD4lTdlJ2B09bBsBGvw3FobMDVyZeAJDCWoi7c6fifRIvAhY4PfrBeQEAdFkAzy08KQ1uSL9rz7NhVOOhIOrix4jljWU17s8Q1YmN_ob7Pv-EeN64sMv2XUdPKpOPAosrHiD8Kddbfl-zFwgYDfBezFBOt-V8ocdw9NR5tqvfAHr9dRH4kdy9Dfjg3yFVCmc2EnBlqexl7058U_LAxoim9S9IqM6B6nJNpWpHDZfEF_jUz3-7zHBjdm1s5ff2i3OMcqn-p73-FYU5jciLC7i8sPdQERao5l46FffhKUdFsv0icxMvKzqX0LQoDF3rIqIVBC32oM7j0kaG_43KBcElKcAiDonNPtdnhQVPzDZlM_lw24Bw8s8d_rJ3D1He8D_o1yhRGIvm4Pw_4k566N0UwpCdvPNdK8iJ1AXERT_AJlMPebGKUVBAgVR_H7gV2_qTAhz7K61tjuvVrFPJFWen5KMCZDTz_WAdAkWo-FvpLBNw_2WUEPcFxkNslIPQD3OXEpdxd3X31KClr8ukigdNucViXLBWulSbqDoTTJdqzEku1F35Jqq2mWWVj8wBEN1A2R13q2whCIx_lgTcVKTQc8HYJiqPRt9oT_NI5rM03ZWO2_Ozd2cFcT0cDKLEUywzGSjIO3pe7oSjPAVJB0K0syFrZ9l_wySWWodhCWpLFED-xHlCA0oZr1kZy1nF8aDsJriW6N3G0JqBLQfhLFRwM269b83RpIXtB3A8HuC1cnNKlOLgy5uOFcASEhda1-ok35hF_V4NAd6035V_aT7uvoMvIHgr6G0D7v6SGaaM6ThuJbWmXecrx8waMH3X6b_owybhF4dD8u7n46Wk9b5DdQB7Z5X9W14ZImF9Rd3jN36rSCj44B6ePE_SjDLNQcaAZxQIeBoheoXNAkS-x-bz5v46164E73Pku-RLD2aviRS1qE_e_EyZ4TBnabfqyLDWnMPbRGOfyhQskm14txATsbT7xlU9IXvphXmtIYBT4auiVuJqEEWuUzaznqdC8sYcMqFMoyOs9C-iOoL26_nPiTr9eouirSELoULuJ7ZSpoFIvLFEaefOAwp4EhrVTU9v5s7-gEYyzcdYmpDkg5Ywaf8Swx8zd-_9T_s0GZc3C7PpnAuYkkeqRTs0pEdrlEy2lVz3l7KisRiB4yFL4lp_y6c4JMDCBZl7Pu6peFPNbCqMx1J2FyCpb1BXjNKgQoE5TI9Gon_c1MNQDIZBeyuI1bMtFcuk3NuxdmVYAGiTJicXKFwMySRViY12iinURsHmdlJ3PCb3IYPsy02TumVGcXUKmN1VZxdlgmmSE58OeHCW2azTv4wX-iyQoqFP8xUXVmIJdN4kfGrw7FeGj97zvwgGU2WlH5EXz5X6VaEzO4huS58_vtMstrdLaifb-ypQTQfEygZ9fPXarYLBPKpErAWal5bnykaV-4EvKlBcQM5avKVwzbpD1gMaEvclpCZxC5yLKb1kpAVLNKA2kWKjlRZBfT6JUXF2OurM7CMh2-QuDvbUacCZxBlGfc8tH5ZipqsC42zhhwO8TJvYOSco-uavmnb9XmDXJYEEvAKLIMeNe2gqxG_pn5jkBBh8ZTxZ22N74AFFVbhOG0-4yvoE_s1eDR6qEZVfJgqHGnG64MbHBYH4XjPdyUohjr-hFslybgDItj0i7QmkgWlK2NKinlW65gcydlgoVIB4V1zklfNZQ3hpXawc5g4YRKEy4ZFVFsKbUtvJC4BDu3i_exC47BQMGwVwPo3EwgZZTTfhRiv8sMlEXiW6910PZQSNS1B3IeH5EFdyh28RZxDZbuJV10N-Jnfo-zBZeOy3Xet_zTc8QbN6okMNpN_amSgfNMfISDtxTEVRDQ1VY1SvfGKDkLEKNZYoPZ_vYWn9gTvc2014OuNSi0Z35ZnEDuLVncnoZnHD7pW9WfNwwpEA19o60TkiEHbT3r1J4jjxnir9BkCRJ3VwfFKP8isToZQN4vBydfrCeiuP8Sn9QDvqaAEeHBNwqsrs0ogEdaGU48rr4C52fyJAi1skr1PTjEK2I66fPlaXWR8CPTsaXwryxtZM_4ExCg1wsWrNceCupvJdsXLAAtYgHMgq58x7wrbPxiWVBMeU_Hz6ain4wkFtubTfwEtBHQ0yXHXJyGRlJR35bROGVScjzj_2nC_hRoWezcdHV57VEms975C-96VS8PilAJDn_P_kagkD1vx69QGO0-3huGMLJjvlt1sIvz3xPMzhKx832QZAOi-WfsN0XtbrLKMkicw&cid=CAASfeRor9wquZxeNG7XwJJO2R9n87ypJw6mbe4YCmeOj5A1K2YwumKVWNt4hKXIQiy4IhpZI-PCZbIAu07SqMeChBAwGVL6VNnALXcDAdl4aeV-WFiInak7fzWnPILKLWnS_4-izMSTRBjE17sgfdTOV60X-gSODI8tpioua8il';document.write('<script src=\\\\\\\"' + (window.rfl ? window.rfl(url) : url) + '\\\\\\\"></s' + 'cript>');</script></div></div><img src=\\\\\\\"https://vektor-us-east-1-eks.axonix.com/supply/pxl/9393766c-24ce-4d10-bb6e-80aca669404b/70f5e2f2a68a45b29dce47798e213ca3?index=0&auctionPrice=1.595429457\\\\\\\" alt=\\\\\\\" \\\\\\\" style=\\\\\\\"display:none\\\\\\\"/><img src=\\\\\\\"https://vektor-us-east-1-eks.axonix.com/supply/trk/9393766c-24ce-4d10-bb6e-80aca669404b/70f5e2f2a68a45b29dce47798e213ca3\\\\\\\" width=\\\\\\\"1\\\\\\\" height=\\\\\\\"1\\\\\\\" alt=\\\\\\\"\\\\\\\" style=\\\\\\\"position: absolute; left: 0px; top: 0px; visibility: hidden;\\\\\\\" /><img src=\\\\\\\"https://vektor-us-east-1-eks.axonix.com/supply/imp/9393766c-24ce-4d10-bb6e-80aca669404b/70f5e2f2a68a45b29dce47798e213ca3\\\\\\\" width=\\\\\\\"1\\\\\\\" height=\\\\\\\"1\\\\\\\" alt=\\\\\\\"\\\\\\\" style=\\\\\\\"position: absolute; left: 0px; top: 0px; visibility: hidden;\\\\\\\" /><script> window.SASAdContainerTypeTrackerConfig = {mraidTrackingUrl: 'https://ssb-use1.smartadserver.com:443/api/event?callerid=40&actionid=10012&rtb=1&rtbnid=3817&rtbbid=922501523137776153&rtbh=f0486d91c43d678d29edeea5b5e7b826b47b3d58&rtblt=637839192022385241&rtbet=2&rtbptnid=101',omidTrackingUrl: 'https://ssb-use1.smartadserver.com:443/api/event?callerid=40&actionid=10013&rtb=1&rtbnid=3817&rtbbid=922501523137776153&rtbh=f0486d91c43d678d29edeea5b5e7b826b47b3d58&rtblt=637839192022385241&rtbet=2&rtbptnid=101'} </script><script src=\\\\\\\"https://apps.sascdn.com/onepx/OnepxImpressionScript.js\\\\\\\"></script>\"}\n";
        saveFile("binaryAd", compress(str));
        System.out.println("原长度：" + str.length());
        System.out.println("压缩后字符串：" + Arrays.toString(GZIPUtils.compress(str)).length());
        System.out.println("解压缩后字符串：" + StringUtils.newStringUtf8(GZIPUtils.uncompress(GZIPUtils.compress(str))));
        System.out.println("解压缩后字符串：" + GZIPUtils.uncompressToString(GZIPUtils.compress(str)));
        System.out.println("解压前后是否相等："+str.equals(GZIPUtils.uncompressToString(GZIPUtils.compress(str))));
    }
}
