package ad.uda.tprats.workitdata.utilities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private long size;
    private Long id;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
