package backend.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import backend.DTO.ImportCreatedDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "import")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ImportStatus status;
    
    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public static ImportCreatedDTO getCreatedImport(Import importObj) {
        return new ImportCreatedDTO(
            importObj.getStatus(),
            importObj.getUserName(),
            importObj.getCount(),
            importObj.getTime().toString(),
            Import.getImportHash(importObj),
            null
        );
    }
    
    public static String getImportHash(Import importObj) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Используем SHA-256 для хэширования
            StringBuilder sb = new StringBuilder();
    
            // Конкатенируем значения полей объекта
            sb.append(importObj.getStatus());
            sb.append(importObj.getUserName());
            sb.append(importObj.getCount());
            sb.append(importObj.getTime());
    
            // Получаем байтовый массив хэша
            byte[] hashBytes = digest.digest(sb.toString().getBytes());
    
            // Преобразуем байты в шестнадцатеричную строку
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
    
            log.info(hexString.toString());
            return hexString.toString(); // Возвращаем хэш в виде строки
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при получении хэша: " + e.getMessage());
        }
    }
}
