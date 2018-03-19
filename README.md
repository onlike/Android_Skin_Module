<h1>Android Skin Module</h1>
<p>dynamic android skin module for application</p>

<h2>Screenshots</h2>
<img src = "https://github.com/onlike/Android_Skin_Module/blob/master/screenshots/demonstrate.gif" title = "sample screenshots"/>
<br/>

<h2>1.How to use?</h2>
<h5> a : import skin module</h5>
<p>at now , skin module just provide libaray mode to import , lool like:</p>
<code>
  <pre>
        compile project(':lib_skin')
  </pre>  
</code>

<h5> b : init skin module</h5>
<p>you must init skin module in your custom application</p>
<code>
  <pre>
      SkinManager.init(this);
  </pre>  
</code>

<h5> c : load skin</h5>
<p>you can load skin file in any where </p>
<code>
  <pre>
      SkinManager.getInstance().load();
      // or
      SkinManager.getInstance().load(SkinLoadCallback callback)
      // or
      SkinManager.getInstance().load(final String skinPath, final SkinLoadCallback callback);
  </pre>
</code>

<h5> d : clear skin</h5>
<p>clear already load skin file and take default skin effictive</p>
<code>
  <pre>
      SkinManager.getInstance().clearSkin();
  </pre>
</code>

<h5> e : about support</h5>
<p>1.library support extanded skin attr</p>
<code>
  <pre>
      SkinManager.getInstance().addSkinAttrType(String attrName, Class<? extends BaseAttr> cls);
  </pre>
</code>
<p>2.library support customize resource parse</p>
<code>
  <pre>
      SkinManager.getInstance().setResourceParse(IResourceParse parse);
  </pre>
</code>

<h2>2.Core logic code.</h2>
<p>View Prase.(take a customize xml prase create view to find skin enable view)</p>
<code>
  <pre>
        LayoutInflaterCompat.setFactory(LayoutInflater inflater, LayoutInflaterFactory factory);
  </pre>
</code>

<p>Resource Prase.(use reflection init a resource object to load assetmanager)</p>
<code>
  <pre>
         AssetManager assetManager = AssetManager.class.newInstance();
         Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
  </pre>
</code>
<code>
  <pre>
         Resources skinResource = new Resources(AssetManager assets, DisplayMetrics metrics, Configuration config);
  </pre>
</code>



















