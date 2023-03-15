package reivosar.common.util.promise;

import reivosar.common.util.lang.ObjectUtil;

class PromiseConfig {
    
    static final int DEFAULT_MULTIPLE_NUMBER = 1;
    static final Long DEFAULT_TIMEOUT_SECOND = 30L;
    
    static final PromiseConfig DEFAULT_CONFIG = new PromiseConfig(DEFAULT_MULTIPLE_NUMBER, DEFAULT_TIMEOUT_SECOND);
    
    final Integer multiple;
    final Long timeout;
    
    private PromiseConfig(final Builder builder) {
        this(builder.multiple, builder.timeout);
    }
    
    private PromiseConfig(final Integer multiple) {
        this(multiple, DEFAULT_TIMEOUT_SECOND);
    }
    
    private PromiseConfig(final Integer multiple, final Long timeout) {
        this.multiple = ObjectUtil.defaultIfNull(multiple, DEFAULT_MULTIPLE_NUMBER);
        this.timeout = ObjectUtil.defaultIfNull(timeout, DEFAULT_TIMEOUT_SECOND);
    }
    
    static PromiseConfig.Builder builder() {
        return new PromiseConfig.Builder();
    }
    
    static class Builder {
        
        private Integer multiple;
        private Long timeout;
        
        Builder multiple(final Integer multiple) {
            this.multiple = multiple;
            return this;
        }
        
        Builder timeout(final Long timeout) {
            this.timeout = timeout;
            return this;
        }
        
        PromiseConfig build() {
            return new PromiseConfig(this);
        }
    }
}
