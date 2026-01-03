package care.jarurat.hope.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InteractiveMessage {

    // Common fields
    private String type;          // button | list
    private String header;
    private String body;
    private String footer;

    // For button messages
    private List<Button> buttons;

    // For list messages
    private Action action;

    /* =======================
       BUTTON SUPPORT
       ======================= */

    @Data
    @Builder
    public static class Button {
        private String id;
        private String title;
        private String url;
        private String type;
    }

    /* =======================
       LIST MESSAGE SUPPORT
       ======================= */

    @Data
    @Builder
    public static class Action {
        private String button;              // Button text like "View Labs"
        private List<Section> sections;
    }

    @Data
    @Builder
    public static class Section {
        private String title;
        private List<Row> rows;
    }

    @Data
    @Builder
    public static class Row {
        private String id;                  // Returned when user clicks
        private String title;
        private String description;
    }

    /* =======================
       FACTORY METHODS
       ======================= */

    // Existing button message (safe)
    public static InteractiveMessage buttonMessage(
            String body,
            List<Button> buttons
    ) {
        return InteractiveMessage.builder()
                .type("button")
                .body(body)
                .buttons(buttons)
                .build();
    }

    // NEW: List message
    public static InteractiveMessage listMessage(
            String header,
            String body,
            String buttonText,
            List<Section> sections
    ) {
        return InteractiveMessage.builder()
                .type("list")
                .header(header)
                .body(body)
                .action(
                        Action.builder()
                                .button(buttonText)
                                .sections(sections)
                                .build()
                )
                .build();
    }
}
