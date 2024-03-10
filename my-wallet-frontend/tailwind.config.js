module.exports = {
  content: ['./src/**/*.{js,jsx,ts,tsx}' /* src folder, for example */,
    'node_modules/flowbite-react/lib/esm/**/*.js'
  ],
  theme: {
    colors:{
      primary:"#649ef900"
    },
    extend: {},
  },
  plugins: [require('flowbite/plugin'),],
};




