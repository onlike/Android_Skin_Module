<h1>Android Skin Module</h1>
<p>dynamic android skin module for application</p>
<ol>
  <li>
    <p>support dynamic add view to skin</p>
  </li>
  
  <li>
    <p>support skin attr extanded to use for most view</p>
  </li>
  
  <li>
    <p>support customize resource parse when you want to do something</p>
  </li>
  
  <li>
    <p>support cold start skin effective</p>
  </li>
  
  <li>
    <p>force prevent fragment create skin view memory leak</p>
  </li>
</ol>
<br/>

<h2>Screenshots</h2>
<img src = "https://github.com/onlike/Android_Skin_Module/blob/master/screenshots/demonstrate.gif" title = "sample screenshots"/>
<h2>1.How to use?</h2>

<h3> a : take skin file</h3>
<p>1.take skin resource in lib_skin_factory project , such as sample , pay attention resource name must same to target .
</p>
<p>2.use gradle command "assembleDebug" or "assembleRelease" in lib_skin_factory project , the skin file will be create in path "/lib_skin_factory/skin_output/".
</p>

<h3> b : import skin module</h3>
<p>at now , skin module just provide libaray mode to import , lool like:</p>
<code>
  <pre>
        compile project(':lib_skin')
  </pre>  
</code>

<h3> c : init skin module</h3>
<p>you must init skin module in your custom application</p>
<code>
  <pre>
      SkinManager.init(this);
  </pre>  
</code>

<h3> d : load skin</h3>
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

<h3> e : clear skin</h3>
<p>clear already load skin file and take default skin effictive</p>
<code>
  <pre>
      SkinManager.getInstance().clearSkin();
  </pre>
</code>

<h3> f : about support</h3>
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

<h3> #tips</h3>
<p>
  <b>
    Do't need call skin load when application already load it at last runtime .
  </b>
</p>
<br/>

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

<h2>3.TODO</h2>
<ol>
  <li>
    support maven center to import
  <li>
    
  <li>
    optimization skin fackory library 
  <li>
    
  <li>
    add project mind map
  <li>   
</ol>

















