package notes.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteCreateRequest {

    private String title;
    private String content;
    private String category;
    private Boolean like;

}
