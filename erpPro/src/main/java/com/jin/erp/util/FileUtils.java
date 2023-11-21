package com.jin.erp.util;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileUtils {


    // MIME TYPE �꽕�젙�쓣 �쐞�븳 留� 留뚮뱾湲�
    private static final Map<String, MediaType> mediaMap;

    static {
        mediaMap = new HashMap<>();
        mediaMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaMap.put("GIF", MediaType.IMAGE_GIF);
        mediaMap.put("PNG", MediaType.IMAGE_PNG);
    }

    // �솗�옣�옄瑜� �븣�젮二쇰㈃ 誘몃뵒�뼱���엯�쓣 由ы꽩�븯�뒗 硫붿꽌�뱶
    public static MediaType getMediaType(String ext) {
        String upperExt = ext.toUpperCase();
        if (mediaMap.containsKey(upperExt)) {
            return mediaMap.get(upperExt);
        }
        return null;
    }



    // 1. �궗�슜�옄媛� �뙆�씪�쓣 �뾽濡쒕뱶�뻽�쓣 �븣 �깉濡쒖슫 �뙆�씪紐낆쓣 �깮�꽦�빐�꽌
    //    諛섑솚�븯怨� �빐�떦 �뙆�씪紐낆쑝濡� �뾽濡쒕뱶�븯�뒗 硫붿꽌�뱶
    // ex) �궗�슜�옄媛� �긽�뼱.jpg瑜� �삱�졇�쑝硫� �씠由꾩쓣 ���옣�븯湲� �쟾�뿉 以묐났�뾾�뒗 �씠由꾩쑝濡� 諛붽퓞

    /**
     *
     * @param file - �겢�씪�씠�뼵�듃媛� �뾽濡쒕뱶�븳 �뙆�씪 �젙蹂�
     * @param uploadPath - �꽌踰꾩쓽 �뾽濡쒕뱶 猷⑦듃 �뵒�젆�넗由� (E:/sl_dev/upload)
     * @return - �뾽濡쒕뱶媛� �셿猷뚮맂 �깉濡쒖슫 �뙆�씪�쓽 full path
     */
    public static String uploadFile(MultipartFile file, String uploadPath) {

        // 以묐났�씠 �뾾�뒗 �뙆�씪紐낆쑝濡� 蹂�寃쏀븯湲�
        // ex) �긽�뼱.png -> 3dfsfjkdsfds-djksfaqwerij-dsjkfdkj_�긽�뼱.png
        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // �뾽濡쒕뱶 寃쎈줈瑜� 蹂�寃�
        // E:/sl_dev/upload  ->  E:/sl_dev/upload/2022/08/01
        String newUploadPath = getNewUploadPath(uploadPath);

        // �뙆�씪 �뾽濡쒕뱶 �닔�뻾
        File f = new File(newUploadPath, newFileName);

        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // �뙆�씪�쓽 �� 寃쎈줈 (�뵒�젆�넗由ш꼍濡� + �뙆�씪紐�)
        String fileFullPath = newUploadPath + File.separator + newFileName;

        // �� 寃쎈줈 - 猷⑦듃 寃쎈줈 臾몄옄�뿴 �깮�꽦
        // full-path => E:/sl_dev/upload/2022/08/01/dfsdjfksfdkjs_�긽�뼱.jpg
        // res-path =>  /2022/08/01/dfsdjfksfdkjs_�긽�뼱.jpg
        // uploadPath => E:/sl_dev/upload
        String responseFilePath = fileFullPath.substring(uploadPath.length());

        return responseFilePath.replace("\\", "/");
    }

    /**
     * �썝蹂� �뾽濡쒕뱶 寃쎈줈瑜� 諛쏆븘�꽌 �씪�옄蹂� �뤃�뜑瑜� �깮�꽦 �븳 �썑 理쒖쥌寃쎈줈瑜� 由ы꽩
     * @param uploadPath - �썝蹂� �뾽濡쒕뱶 寃쎈줈
     * @return �씪�옄蹂� �뤃�뜑媛� �룷�븿�맂 �깉濡쒖슫 �뾽濡쒕뱶 寃쎈줈
     */
    private static String getNewUploadPath(String uploadPath) {

        // �삤�뒛 �뀈,�썡,�씪 �젙蹂� 媛��졇�삤湲�
        LocalDateTime now = LocalDateTime.now();
        int y = now.getYear();
        int m = now.getMonthValue();
        int d = now.getDayOfMonth();

        // �뤃�뜑 �깮�꽦
        String[] dateInfo = {
                String.valueOf(y)
                , len2(m)
                , len2(d)
        };

        String newUploadPath = uploadPath;

        // File.separator : �슫�쁺泥댁젣�뿉 留욌뒗 �뵒�젆�넗由� 寃쎈줈援щ텇臾몄옄瑜� �깮�꽦
        // 由щ늼�뒪 : / ,  �쐢�룄�슦 : \
        for (String date : dateInfo) {
            newUploadPath += File.separator + date;

            // �빐�떦 寃쎈줈��濡� �뤃�뜑瑜� �깮�꽦
            File dirName = new File(newUploadPath);
            if (!dirName.exists()) dirName.mkdir();
        }

        return newUploadPath;
    }

    // �븳�옄由ъ닔 �썡, �씪 �젙蹂대�� �빆�긽 2�옄由щ줈 留뚮뱾�뼱二쇰뒗 硫붿꽌�뱶
    private static String len2(int n) {
        return new DecimalFormat("00").format(n);
    }

    // �뙆�씪紐낆쓣 諛쏆븘�꽌 �솗�옣�옄瑜� 諛섑솚�븯�뒗 硫붿꽌�뱶
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
