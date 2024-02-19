package notes.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteUpdateRequest {

    private String title;
    private String content;
    private String category;
    private Boolean like;

}
