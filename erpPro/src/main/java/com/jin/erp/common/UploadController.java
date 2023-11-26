package com.jin.erp.common;

import com.jin.erp.util.FileUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {

    // ���ε� ���� ���� ���
    private static final String UPLOAD_PATH = "C:\\allProject\\ERPwork\\erpPro\\src\\main\\webapp\\resources\\upload";

    // upload-form.jsp�� �������ϴ� ��û
    @GetMapping("/upload-form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    // ���� ���ε� ó���� ���� ��û
    // MultipartFile: Ŭ���̾�Ʈ�� ������ ���� �������� ���� ��ü
    // ex) ���� ���ϸ�, ���� �뷮, ���� ������Ÿ��...
    @PostMapping("/upload")
    public String upload(@RequestParam("file") List<MultipartFile> fileList) {
    	System.out.println("upload");
        for (MultipartFile file: fileList) {
            System.out.println("==================================================================");

            // ������ ���ε����� ����

            // 1. ���̺����� ��ü ����
            //  - ù��° �Ķ���ʹ� ���� ������ ����, �ι�° ���ϸ�����
        /*File f = new File(uploadPath, file.getOriginalFilename());

        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

            FileUtils.uploadFile(file, UPLOAD_PATH);
        }

        return "redirect:/upload-form";
    }

    // �񵿱� ��û ���� ���ε� ó��
    @PostMapping("/ajax-upload")
    @ResponseBody
    public String ajaxUpload(MultipartFile files, Model model) {
        // Ŭ���̾�Ʈ���� ������ ���ϰ�� ����Ʈ
        // Ŭ���̾�Ʈ�� ������ ���� ���ε��ϱ�
        String fullPath = FileUtils.uploadFile(files, UPLOAD_PATH);
        System.out.println("���� �̸�:"+fullPath);
        model.addAttribute("filename", fullPath);    
        return fullPath;
    }

    // ���� ������ �ε� ��û ó��
    /*
        �񵿱� ��� ����� ResponseEntity�� ���� ������
        �� ��ü�� ���� body���� �̿ܿ��� header������ ������ �� �ְ�
        �߰��� ���� �����ڵ嵵 ������ �� �ִ�.
     */
    @GetMapping("/loadFile")
    @ResponseBody
    // fileName = /2022/08/01/��ȯ�� ���ϸ�
    public ResponseEntity<byte[]> loadFile(String fileName) {
    	System.out.println("loadFile");
        // Ŭ���̾�Ʈ�� ��û�ϴ� ������ ��¥ ����Ʈ �����͸� ������� ��.

        // 1. ��û ���� ã�Ƽ� file��ü�� ����
        File f = new File(UPLOAD_PATH + fileName);

        if (!f.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 2. �ش� ������ InputStream�� ���� �ҷ��´�.
        try (FileInputStream fis = new FileInputStream(f)) {

            // 3. Ŭ���̾�Ʈ���� ���� �̹����� �����ؾ� �ϹǷ� MIME TYPE�� ��������� ����
            // ex) image/jpeg, image/png, image/gif
            // Ȯ���ڸ� �����ؾ� ��.
            String ext = FileUtils.getFileExtension(fileName);
            MediaType mediaType = FileUtils.getMediaType(ext);

            // ��������� �̵�� Ÿ�� ����
            HttpHeaders headers = new HttpHeaders();

            if (mediaType != null) { // �̹������
                headers.setContentType(mediaType);
            } else { // �̹����� �ƴϸ� �ٿ�ε� �����ϰ� ����
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                // ���ϸ��� ������� ����
                fileName = fileName.substring(fileName.lastIndexOf("_") + 1);

                // ���ϸ��� �ѱ��� ��� ���ڵ� �缳��
                String encoding = new String(
                        fileName.getBytes("UTF-8"), "ISO-8859-1");

                // ����� �� ����� �߰�
                headers.add("Content-Disposition"
                        , "attachment; fileName=\"" + encoding + "\"");

            }

            // 4. ���� ���������� ����Ʈ�迭�� ����.
            byte[] rawData = IOUtils.toByteArray(fis);

            // 5. �񵿱���ſ��� ������ ������ �� ResponseEntity��ü�� ���
            return new ResponseEntity<>(rawData, headers, HttpStatus.OK); // Ŭ���̾�Ʈ�� ���� ������ ����

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //������ �ִ� ���� ���� ��ûó��
    //URI: /deleteFile?fileName=/2019/09/22/s_djfksldfjs_abc.jpg
    @DeleteMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String fileName) throws Exception {

        try {
            //���� ����
            File delFile = new File(UPLOAD_PATH + fileName);
            if (!delFile.exists()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            delFile.delete();

            return new ResponseEntity<>("file-del-success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}