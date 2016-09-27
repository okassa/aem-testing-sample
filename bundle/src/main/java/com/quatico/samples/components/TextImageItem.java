package com.quatico.samples.components;


import com.quatico.samples.AbstractItem;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;


@Getter
@Setter
@Accessors(chain = true)
public class TextImageItem extends AbstractItem<TextImageItem> {

    public static final String IMAGE_PATH_EXTENSION = "image";
    public static final String TEXT_PATH_EXTENSION = "text";

    private TextItem text;
    private ImageItem image;

    @Override
    public TextImageItem fill(Resource resource) {
        if (resource != null) {
            setPath(resource.getPath());
            this.image = new ImageItem().fill(resource.getChild(IMAGE_PATH_EXTENSION));
            this.text = new TextItem().fill(resource.getChild(TEXT_PATH_EXTENSION));
        }
        return this;
    }

    @Override
    public boolean isValid() {
        return this.text != null && text.isValid()
                && StringUtils.isNotBlank(text.getTitle())
                || this.image != null && image.isValid();
    }
}
