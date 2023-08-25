package com.apkdoandroid.demo_token_refresh.model;

public class TokenResponse {

    /**
     * access_token : eyJ4NXQiOiJZVGRsTXpaa1pHTmxOak15WldVd05USmhNV0ZoWXpJNU5HSTNaVEJsTmpZeFptTXpPVFZqWkEiLCJraWQiOiJOelV4TkdGbVlqRTFOakJsTVRFellUWTNOMlF6T1RBd09ERTNOMkkxTVRabU5HVTRPV1l3TkRobE9UWTNOekEyWlRGaVlXTXdOR0V3TWpCaU5ETTNOQV9SUzI1NiIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJhdXRlbnRpa3VzIiwiYXV0IjoiQVBQTElDQVRJT04iLCJhdWQiOiJjV1YwTDFmRTUxd055Z1RGT3BWVWs2QnRTYUVhIiwibmJmIjoxNjkyODA1NDU4LCJhenAiOiJjV1YwTDFmRTUxd055Z1RGT3BWVWs2QnRTYUVhIiwic2NvcGUiOiJkZWZhdWx0IiwiaXNzIjoiaHR0cHM6XC9cL3B1Ymxpc2hlci5hcGlzZXJwcm8uc2VycHJvLmdvdi5icjo0NDNcL29hdXRoMlwvdG9rZW4iLCJyZWFsbSI6eyJzaWduaW5nX3RlbmFudCI6ImNhcmJvbi5zdXBlciJ9LCJleHAiOjE2OTI4MDkwNTgsImlhdCI6MTY5MjgwNTQ1OCwianRpIjoiOTg4MDcyYmMtY2MxZi00Y2Q4LTkxMWItMjUxZjQwYmU4YzdjIn0.VZEmrRa4NjOFwOqbsLx_w4FxJjC1vu9oOWaxRHSX5eieWPEWwayRFUs1d-o1kCsu7RA6Z5f8LGdeZ-WuWV0_-g7wNCvCMJ-1UbfiMPwHU2gFbajXVisPILMgaJ6pJp6-XBIz23bSAQy1Wv9vPUaRsTjn2EH6-SObC4KLlHZWIKeD1QXWWUWgIjx6lYhp2MylyQskkhxAyXxcMuOKpuy-hg88LJ6kr6VNVIpYJzO00TnJ1gEmgrQYYJOPAL1W_gA3RqYUeq0R_mJ660QznhG0iOB8efvsDtDoxnvRQa2XbYaU0vt-DxdrvJqOQx3QnL56UOpZLW7SxGduBXF9Ly_Fow
     * scope : default
     * token_type : Bearer
     * expires_in : 3600
     */

    private String access_token;
    private String scope;
    private String token_type;
    private int expires_in;

    public TokenResponse() {
    }

    public TokenResponse(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", scope='" + scope + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
