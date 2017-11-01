<template>
  <div class="hello">
    <canvas style="margin-left: auto; margin-right: auto;" id="webglcanvas" width="800 " height="600"></canvas>
    <br/>
    <i>The graphics demo is above</i>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}



setTimeout(() => {



  const canvas = document.getElementById('webglcanvas');
  const gl = canvas.getContext("webgl") || canvas.getContext("experimental-webgl");
  
  gl.clearColor(1, 0, 1, 1);
  gl.clear(gl.COLOR_BUFFER_BIT);

  const vertexShader = gl.createShader(gl.VERTEX_SHADER);
  gl.shaderSource(vertexShader, `
    attribute vec2 position;
    void main() {
      gl_Position = vec4(position, 0.0, 1.0);
    }  
  `);
  gl.compileShader(vertexShader);


  const fragmentShader = gl.createShader(gl.FRAGMENT_SHADER);
  gl.shaderSource(fragmentShader, `
    precision highp float;
    uniform vec4 color;
    void main() {
      gl_FragColor = color;
    }
  `);
  gl.compileShader(fragmentShader);

  const program = gl.createProgram();
  gl.attachShader(program, vertexShader);
  gl.attachShader(program, fragmentShader);
  gl.linkProgram(program);


  const vertices = new Float32Array([
    -0.5, -0.5,
    0.5, -0.5,
    0.0, 0.5
  ]);

  const buffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, buffer);
  gl.bufferData(gl.ARRAY_BUFFER, vertices, gl.STATIC_DRAW);

  gl.useProgram(program);
  program.color = gl.getUniformLocation(program, 'color');
  gl.uniform4fv(program.color, [0, 1, 0, 1.0]);

  program.position = gl.getAttribLocation(program, 'position')
  gl.enableVertexAttribArray(program.position);
  gl.vertexAttribPointer(program.position, 2, gl.FLOAT, false, 0, 0);

  gl.drawArrays(gl.TRIANGLES, 0, vertices.length / 2);











  function createProgram(gl, vertexShaderSource, fragmentShaderSource) {
    const vsh = gl.createShader(gl.VERTEX_SHADER);
    gl.shaderSource(vsh, vertexShaderSource);
    gl.compileShader(vsh);
    if(!gl.getShaderParameter(vsh, gl.COMPILE_STATUS)) {
      throw "Error in vertex shader: " + gl.getShaderInfoLog(vsh);
    }

    const fsh = gl.createShader(gl.FRAGMENT_SHADER);
    gl.shaderSource(fsh, fragmentShaderSource);
    gl.compileShader(fsh);
    if(!gl.getShaderParameter(fsh, gl.COMPILE_STATUS)) {
      throw "Error in fragment shader: " + gl.getShaderInfoLog(fsh);
    }

    const prog = gl.createProgram();
    gl.attachShader(prog, vsh);
    gl.attachShader(prog, fsh);
    gl.linkProgram(prog);

    if(!gl.getProgramParameter(prog, gl.LINK_STATUS)) {
      throw "Link error in program: " + gl.getProgramInfoLog(prog);
    }

    return prog;
  }






}, 5000);





</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
