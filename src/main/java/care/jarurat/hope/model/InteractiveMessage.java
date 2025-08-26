package care.jarurat.hope.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InteractiveMessage {
    private String header;
    private String body;
    private String footer;
    private List<Button> buttons;

    @Data
    @Builder
    public static class Button {
        private String id;
        private String title;
        private String url;
        private String type;
    }
}