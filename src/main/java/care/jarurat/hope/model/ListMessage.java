package care.jarurat.hope.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListMessage {
    private String header;
    private String body;
    private String footer;
    private String buttonText;
    private List<Section> sections;

    @Data
    @Builder
    public static class Section {
        private String title;
        private List<Row> rows;
    }

    @Data
    @Builder
    public static class Row {
        private String id;
        private String title;
        private String description;
    }
}
